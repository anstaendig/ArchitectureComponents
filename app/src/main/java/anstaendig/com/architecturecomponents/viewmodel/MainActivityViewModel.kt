package anstaendig.com.architecturecomponents.viewmodel

import anstaendig.com.architecturecomponents.repository.Action
import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.repository.Result
import anstaendig.com.architecturecomponents.ui.MainActivityViewState
import anstaendig.com.architecturecomponents.ui.UiEvent
import anstaendig.com.architecturecomponents.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

class MainActivityViewModel
@Inject
constructor(repository: Repository) : BaseViewModel<MainActivityViewState>() {

  var initialState: MainActivityViewState = MainActivityViewState.Idle

  val onTextChange: ObservableTransformer<UiEvent.OnTextChange, Action.LoadPerson> = ObservableTransformer { events ->
    events.map { (text) -> Action.LoadPerson(text) }
  }

  val onMessageClick: ObservableTransformer<UiEvent.OnMessageClick, Action.Two> = ObservableTransformer { events ->
    events.map { Action.Two }
  }

  // TODO: Use RxRelay instead of subjects
  val events: Subject<UiEvent> = BehaviorSubject.create<UiEvent>()

  val actions: ObservableTransformer<UiEvent, Action> = ObservableTransformer { events ->
    events.publish<Action> { shared ->
      Observable.merge(
          shared.ofType(UiEvent.OnTextChange::class.java).compose(onTextChange),
          shared.ofType(UiEvent.OnMessageClick::class.java).compose(onMessageClick)
      )
    }
  }

  val state: Observable<MainActivityViewState> =
      events
          .compose(actions)
          .compose(repository.results)
          .scan<MainActivityViewState>(initialState, { state, result ->
            initialState = state
            when (result) {
              is Result.Success -> MainActivityViewState.Success(result.data)
              is Result.Failure -> MainActivityViewState.Failure(result.e)
              is Result.InProgress -> MainActivityViewState.InProgress
            }
          })

  init {
    disposables.addAll(
        state.subscribe({ state ->
          viewState.value = state
        }, { throwable ->
          throw OnErrorNotImplementedException(throwable)
        })
    )
  }
}

