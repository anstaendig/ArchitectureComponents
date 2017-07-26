package com.anstaendig.base.ui

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.anstaendig.base.viewmodel.BaseViewModel
import com.anstaendig.base.ui.event.UiEvent
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<M : BaseViewModel<S>, S : BaseViewState, T : UiEvent>
  : AppCompatActivity(), LifecycleRegistryOwner, HasSupportFragmentInjector {

  @Inject
  protected lateinit var viewModelFactory: ViewModelProvider.Factory
  protected abstract val viewModelClass: Class<M>
  protected val viewModel: M by lazy(LazyThreadSafetyMode.NONE) {
    ViewModelProviders.of(this, viewModelFactory).get(viewModelClass).also { it.init() }
  }

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  protected val disposables = CompositeDisposable()

  protected abstract val events: Observable<T>
  protected abstract val layoutResource: Int

  // We have to hold a strong reference to the instance of LifecycleRegistry, otherwise GC will
  // collect it and LifecycleObserver of LiveData won’t see the active observers from LifecycleOwner
  // and our views won’t receive the updates.
  @Suppress("LeakingThis")
  private val lifecycleRegistry = LifecycleRegistry(this)
  override fun getLifecycle() = lifecycleRegistry

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
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

  override fun supportFragmentInjector() = fragmentInjector

  protected abstract fun render(viewState: S)
}
