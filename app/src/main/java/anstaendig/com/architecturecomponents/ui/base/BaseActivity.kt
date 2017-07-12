package anstaendig.com.architecturecomponents.ui.base

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import anstaendig.com.architecturecomponents.ui.event.UiEvent
import anstaendig.com.architecturecomponents.viewmodel.base.BaseViewModel
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<M : BaseViewModel<S>, S : BaseViewState, T : UiEvent>
  : AppCompatActivity(), LifecycleRegistryOwner {

  @Inject
  private lateinit var viewModelFactory: ViewModelProvider.Factory
  protected val viewModel: M by lazy(LazyThreadSafetyMode.NONE) {
    ViewModelProviders.of(this, viewModelFactory).get(viewModelClass).also { it.init() }
  }

  // We have to hold a strong reference to the instance of LifecycleRegistry, otherwise GC will
  // collect it and LifecycleObserver of LiveData won’t see the active observers from LifecycleOwner
  // and our views won’t receive the updates.
  @Suppress("LeakingThis")
  private val lifecycleRegistry = LifecycleRegistry(this)

  protected val disposables = CompositeDisposable()

  protected abstract val events: Observable<T>
  protected abstract val layoutResource: Int
  protected abstract val viewModelClass: Class<M>

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(layoutResource)
    viewModel.viewState.observe(this, Observer<S> { state ->
      state?.let { render(it) }
    })
  }

  override fun onResume() {
    super.onResume()
    disposables.add(events.subscribe { event ->
      viewModel.events.onNext(event)
    })
  }

  override fun onPause() {
    disposables.dispose()
    super.onPause()
  }

  override fun getLifecycle() = lifecycleRegistry

  protected abstract fun render(viewState: S)
}
