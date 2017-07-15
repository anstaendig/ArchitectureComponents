package anstaendig.com.architecturecomponents.viewmodel

import anstaendig.com.architecturecomponents.ui.ExampleFragmentViewState
import anstaendig.com.architecturecomponents.viewmodel.base.BaseViewModel
import io.reactivex.Observable

class ExampleFragmentViewModel : BaseViewModel<ExampleFragmentViewState>() {

  override val state: Observable<ExampleFragmentViewState>
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}
