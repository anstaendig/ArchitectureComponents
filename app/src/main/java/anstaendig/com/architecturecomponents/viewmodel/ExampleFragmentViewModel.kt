package anstaendig.com.architecturecomponents.viewmodel

import anstaendig.com.architecturecomponents.ui.ExampleFragmentViewState
import com.anstaendig.base.viewmodel.BaseViewModel
import io.reactivex.Observable

class ExampleFragmentViewModel : BaseViewModel<ExampleFragmentViewState>() {

    override val state: Observable<ExampleFragmentViewState> = TODO("not implemented")
}
