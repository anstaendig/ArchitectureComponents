package com.anstaendig.base.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.anstaendig.base.ui.event.UiEvent
import com.anstaendig.base.viewmodel.BaseViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<M : BaseViewModel<S>, S : BaseViewState, T : UiEvent>
    : AppCompatActivity(), HasSupportFragmentInjector {

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

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        viewModel.viewState.observe(
                this,
                Observer<S> { state ->
                    state?.let { render(it) }
                }
        )
    }

    override fun onResume() {
        super.onResume()
        disposables.add(
                events.subscribe { event ->
                    viewModel.events.accept(event)
                }
        )
    }

    override fun onPause() {
        disposables.dispose()
        super.onPause()
    }

    override fun supportFragmentInjector() = fragmentInjector

    protected abstract fun render(viewState: S)
}
