package anstaendig.com.architecturecomponents.viewmodel.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import anstaendig.com.architecturecomponents.ui.base.BaseViewState
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<S : BaseViewState> : ViewModel() {

  protected val disposables = CompositeDisposable()

  val viewState = MutableLiveData<S>()

  override fun onCleared() {
    super.onCleared()
    disposables.dispose()
  }
}
