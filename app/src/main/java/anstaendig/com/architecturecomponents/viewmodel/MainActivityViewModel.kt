package anstaendig.com.architecturecomponents.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.ui.MainActivityViewState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel
@Inject
constructor(repository: Repository) : ViewModel() {

  private val disposables = CompositeDisposable()

  val viewState = MutableLiveData<MainActivityViewState>()

  init {
    disposables.add(
        repository.loadPerson("1")
//            .toObservable()
            .map { personData -> MainActivityViewState(personData.name) }
            .onErrorReturn { error -> MainActivityViewState(error.localizedMessage) }
//            .observeOn(AndroidSchedulers.mainThread())
//            .startWith { MainActivityViewState("loading") }
            .subscribe({ state ->
              viewState.postValue(state)
            }, { throwable ->
              throw IllegalStateException()
            })
    )
  }

  override fun onCleared() {
    super.onCleared()
    disposables.clear()
  }
}
