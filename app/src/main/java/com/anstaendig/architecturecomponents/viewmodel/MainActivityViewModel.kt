package com.anstaendig.architecturecomponents.viewmodel

import com.anstaendig.architecturecomponents.repository.SWAction
import com.anstaendig.architecturecomponents.repository.SWRepository
import com.anstaendig.architecturecomponents.repository.SWResult
import com.anstaendig.architecturecomponents.ui.MainActivityUiEvent
import com.anstaendig.architecturecomponents.ui.MainActivityViewState
import com.anstaendig.base.ui.event.UiEvent
import com.anstaendig.base.viewmodel.BaseViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class MainActivityViewModel
@Inject
constructor(repository: SWRepository) : BaseViewModel<MainActivityViewState>() {

    private val onTextChange: ObservableTransformer<MainActivityUiEvent.OnTextChange, SWAction.LoadPerson> =
            ObservableTransformer { events ->
                events.distinctUntilChanged()
                        .map { (text) -> SWAction.LoadPerson(text) }
            }

    private val onMessageClick: ObservableTransformer<MainActivityUiEvent.OnMessageClick, SWAction.LoadLuke> =
            ObservableTransformer { events ->
                events.map { SWAction.LoadLuke }
            }

    private val actions: ObservableTransformer<UiEvent, SWAction> =
            ObservableTransformer { events ->
                events.publish<SWAction> { shared ->
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
                    .scan<MainActivityViewState>(
                            MainActivityViewState.Idle,
                            { _, result ->
                                when (result) {
                                    is SWResult.Success -> MainActivityViewState.Success(result.data)
                                    is SWResult.Failure -> MainActivityViewState.Failure(result.e)
                                    is SWResult.InProgress -> MainActivityViewState.InProgress
                                }
                            }
                    )
}

