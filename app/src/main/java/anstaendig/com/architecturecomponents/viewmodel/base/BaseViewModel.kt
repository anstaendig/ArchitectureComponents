package anstaendig.com.architecturecomponents.viewmodel.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import anstaendig.com.architecturecomponents.ui.base.BaseViewState
import anstaendig.com.architecturecomponents.ui.event.UiEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.subjects.BehaviorSubject

abstract class BaseViewModel<S : BaseViewState> : ViewModel() {

  val disposables = CompositeDisposable()
  val events: BehaviorSubject<UiEvent> = BehaviorSubject.create<UiEvent>()
  val viewState: MutableLiveData<S> = MutableLiveData()

  abstract val state: Observable<S>

  @CallSuper
  open fun init() {
    disposables.addAll(
        state.subscribe({ state ->
          viewState.value = state
        }, { throwable ->
          throw OnErrorNotImplementedException(throwable)
        })
    )
  }

  override fun onCleared() {
    super.onCleared()
    disposables.dispose()
  }
}
