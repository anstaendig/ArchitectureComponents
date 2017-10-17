package com.anstaendig.architecturecomponents.ui

import com.anstaendig.architecturecomponents.R
import com.anstaendig.architecturecomponents.viewmodel.ExampleFragmentViewModel
import com.anstaendig.base.ui.BaseFragment
import com.anstaendig.base.ui.event.UiEvent
import io.reactivex.Observable

class ExampleFragment
    : BaseFragment<ExampleFragmentViewModel, ExampleFragmentViewState, ExampleFragmentUiEvent>() {

    override val viewModelClass = ExampleFragmentViewModel::class.java

    override val events: Observable<ExampleFragmentUiEvent> = TODO("not implemented")

    override val layoutResource = R.layout.f_example

    override fun render(viewState: ExampleFragmentViewState) {
        TODO("not implemented")
    }
}

sealed class ExampleFragmentUiEvent : UiEvent()
