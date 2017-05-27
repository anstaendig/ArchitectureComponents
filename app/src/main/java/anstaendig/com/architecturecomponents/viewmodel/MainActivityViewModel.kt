package anstaendig.com.architecturecomponents.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import anstaendig.com.architecturecomponents.datasource.PageData
import anstaendig.com.architecturecomponents.datasource.PersonData
import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.util.toLiveData
import javax.inject.Inject

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {

  @Inject lateinit var repository: Repository

  val liveData: LiveData<PageData<PersonData>> by lazy {
    repository.loadPeople().toLiveData()
  }
}
