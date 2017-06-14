package anstaendig.com.architecturecomponents.viewmodel

import android.util.Log
import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.ui.MainActivityViewState
import anstaendig.com.architecturecomponents.ui.UiEvent
import anstaendig.com.architecturecomponents.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class MainActivityViewModel
@Inject
constructor(repository: Repository) : BaseViewModel<MainActivityViewState>() {

  val subject: Subject<UiEvent> = BehaviorSubject.create<UiEvent>()

  init {
    disposables.addAll(
        repository.loadPerson("1")
            .toObservable()
            .map<MainActivityViewState> { personData -> MainActivityViewState.Success(personData) }
            .onErrorReturn { error -> MainActivityViewState.Error(error.message!!) }
            .observeOn(AndroidSchedulers.mainThread())
            .startWith(MainActivityViewState.Loading)
            .subscribe({ state ->
              viewState.value = state
            }, { throwable ->
              throw OnErrorNotImplementedException(throwable)
            }),

        subject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ action ->
              when (action) {
                is UiEvent.OnTextChange -> {
                  Log.d("UiEvent subject", "OnTextChange: ${action.text}")
//                  viewState.value = MainActivityViewState.Success(action.text)
                }
              }
            }, { throwable ->
              Log.e("whatever", throwable.message)
            })
    )
  }
}
