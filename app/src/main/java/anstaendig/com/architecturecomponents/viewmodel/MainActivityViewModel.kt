package anstaendig.com.architecturecomponents.viewmodel

import anstaendig.com.architecturecomponents.repository.Action
import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.repository.Result
import anstaendig.com.architecturecomponents.ui.MainActivityUiEvent
import anstaendig.com.architecturecomponents.ui.MainActivityViewState
import anstaendig.com.architecturecomponents.ui.event.UiEvent
import anstaendig.com.architecturecomponents.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class MainActivityViewModel
@Inject
constructor(repository: Repository) : BaseViewModel<MainActivityViewState>() {

  private val onTextChange: ObservableTransformer<MainActivityUiEvent.OnTextChange, Action.LoadPerson> = ObservableTransformer { events ->
    events.distinctUntilChanged()
        .map { (text) -> Action.LoadPerson(text) }
  }

  private val onMessageClick: ObservableTransformer<MainActivityUiEvent.OnMessageClick, Action.LoadLuke> = ObservableTransformer { events ->
    events.map { Action.LoadLuke }
  }

  private val actions: ObservableTransformer<UiEvent, Action> = ObservableTransformer { events ->
    events.publish<Action> { shared ->
      Observable.merge(
          shared.ofType(MainActivityUiEvent.OnTextChange::class.java).compose(onTextChange),
          shared.ofType(MainActivityUiEvent.OnMessageClick::class.java).compose(onMessageClick)
      )
    }
  }

  override val state: Observable<MainActivityViewState> =
      events
          .compose(actions)
          .compose(repository.results)
          .scan<MainActivityViewState>(MainActivityViewState.Idle, { state, result ->
            when (result) {
              is Result.Success -> MainActivityViewState.Success(result.data)
              is Result.Failure -> MainActivityViewState.Failure(result.e)
              is Result.InProgress -> MainActivityViewState.InProgress
            }
          })

  init {
    init()
  }
}

