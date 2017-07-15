package anstaendig.com.architecturecomponents.viewmodel

import anstaendig.com.architecturecomponents.ui.ExampleFragmentViewState
import anstaendig.com.architecturecomponents.viewmodel.base.BaseViewModel
import io.reactivex.Observable

class ExampleFragmentViewModel : BaseViewModel<ExampleFragmentViewState>() {

  override val state: Observable<ExampleFragmentViewState> = TODO("not implemented")
}
