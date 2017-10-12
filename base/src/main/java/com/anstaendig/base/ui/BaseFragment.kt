package com.anstaendig.base.ui

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anstaendig.base.viewmodel.BaseViewModel
import com.anstaendig.base.ui.event.UiEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<M : BaseViewModel<S>, S : BaseViewState, T : UiEvent>
    : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected abstract val viewModelClass: Class<M>
    protected val viewModel: M by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass).also { it.init() }
    }

    protected val disposables = CompositeDisposable()

    protected abstract val events: Observable<T>
    protected abstract val layoutResource: Int

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(
                this,
                Observer<S> { state ->
                    state?.let { render(it) }
                }
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View = inflater.inflate(layoutResource, container, false)

    override fun onResume() {
        super.onResume()
        disposables.add(
                events.subscribe { event ->
                    viewModel.events.onNext(event)
                }
        )
    }

    override fun onPause() {
        disposables.dispose()
        super.onPause()
    }

    protected abstract fun render(viewState: S)
}
