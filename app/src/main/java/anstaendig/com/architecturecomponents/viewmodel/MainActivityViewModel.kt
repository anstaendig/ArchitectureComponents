package anstaendig.com.architecturecomponents.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import anstaendig.com.architecturecomponents.App
import anstaendig.com.architecturecomponents.injection.MainActivityModule
import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.ui.MainActivityViewState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {

  @Inject lateinit var repository: Repository

  private val disposables = CompositeDisposable()

  val viewState = MutableLiveData<MainActivityViewState>()

  init {
    (app as App).appComponent.plus(MainActivityModule()).inject(this)
    disposables.add(
        repository.loadPerson("1")
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
