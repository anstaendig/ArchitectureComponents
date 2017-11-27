package com.anstaendig.architecturecomponents.base.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import com.anstaendig.architecturecomponents.base.ui.BaseViewState
import com.anstaendig.architecturecomponents.base.ui.event.UiEvent
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException

abstract class BaseViewModel<S : BaseViewState> : ViewModel() {

    val disposables = CompositeDisposable()
    val events: PublishRelay<UiEvent> = PublishRelay.create()
    val viewState: MutableLiveData<S> = MutableLiveData()

    abstract val state: Observable<S>

    @CallSuper
    open fun init() {
        disposables.addAll(
                state.subscribe({ state ->
                    viewState.value = state
                }) { throwable ->
                    throw OnErrorNotImplementedException(throwable)
                }
        )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}
