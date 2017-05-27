package anstaendig.com.architecturecomponents.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import anstaendig.com.architecturecomponents.datasource.PageData
import anstaendig.com.architecturecomponents.datasource.PersonData
import anstaendig.com.architecturecomponents.repository.Repository
import javax.inject.Inject

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {

  @Inject lateinit var repository: Repository

  lateinit var liveData: LiveData<PageData<PersonData>>

  fun init() {
    liveData = LiveDataReactiveStreams.fromPublisher(repository.loadPeople().toFlowable())
  }
}
