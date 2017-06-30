package anstaendig.com.architecturecomponents.viewmodel.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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

  override fun onCleared() {
    super.onCleared()
    disposables.dispose()
  }

  protected fun init(state: Observable<S>) {
    disposables.addAll(
        state.subscribe({ state ->
          viewState.value = state
        }, { throwable ->
          throw OnErrorNotImplementedException(throwable)
        })
    )
  }
}
