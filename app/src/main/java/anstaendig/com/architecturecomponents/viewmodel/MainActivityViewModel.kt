package anstaendig.com.architecturecomponents.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.ui.MainActivityViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import javax.inject.Inject

class MainActivityViewModel
@Inject
constructor(repository: Repository) : ViewModel() {

  private val disposables = CompositeDisposable()

  val viewState = MutableLiveData<MainActivityViewState>()

  init {
    disposables.add(
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
            })
    )
  }

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }
}
