package anstaendig.com.architecturecomponents.viewmodel

import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.ui.MainActivityViewState
import anstaendig.com.architecturecomponents.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.OnErrorNotImplementedException
import javax.inject.Inject

class MainActivityViewModel
@Inject
constructor(repository: Repository) : BaseViewModel<MainActivityViewState>() {

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
}
