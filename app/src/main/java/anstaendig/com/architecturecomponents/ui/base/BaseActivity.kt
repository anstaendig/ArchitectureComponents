package anstaendig.com.architecturecomponents.ui.base

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import anstaendig.com.architecturecomponents.viewmodel.base.BaseViewModel
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<M : BaseViewModel<S>, S : BaseViewState>
  : AppCompatActivity(), LifecycleRegistryOwner {

  @Inject
  protected lateinit var viewModelFactory: ViewModelProvider.Factory

  protected val viewModel: M by lazy {
    ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
  }
  protected val disposables = CompositeDisposable()

  // We have to hold a strong reference to the instance of LifecycleRegistry, otherwise GC will
  // collect it and LifecycleObserver of LiveData won’t see the active observers from LifecycleOwner
  // and our views won’t receive the updates.
  @Suppress("LeakingThis")
  private val lifecycleRegistry = LifecycleRegistry(this)

  abstract val layoutResource: Int
  abstract val viewModelClass: Class<M>

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(layoutResource)
    viewModel.viewState.observe(this, Observer<S> { state ->
      state?.let { render(it) }
    })
  }

  override fun onPause() {
    disposables.dispose()
    super.onPause()
  }

  override fun getLifecycle() = lifecycleRegistry

  abstract fun render(viewState: S)
}
