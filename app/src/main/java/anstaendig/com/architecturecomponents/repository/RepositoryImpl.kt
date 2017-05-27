package anstaendig.com.architecturecomponents.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.net.Uri
import anstaendig.com.architecturecomponents.datasource.PageData
import anstaendig.com.architecturecomponents.datasource.PersonData
import anstaendig.com.architecturecomponents.datasource.SwapiService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl
@Inject
constructor(private val swapiService: SwapiService): Repository {

  override fun loadPeople(): Single<PageData<PersonData>> = swapiService.loadPeople()
      .subscribeOn(Schedulers.io())

  override fun loadPeople(url: Uri): LiveData<PageData<PersonData>>
      = LiveDataReactiveStreams.fromPublisher<PageData<PersonData>> { swapiService.loadPeople(url) }

  override fun searchPeople(name: String): LiveData<PageData<PersonData>>
      = LiveDataReactiveStreams.fromPublisher<PageData<PersonData>> { swapiService.searchPeople(name) }

  override fun loadPerson(id: String): LiveData<PersonData>
      = LiveDataReactiveStreams.fromPublisher<PersonData> { swapiService.loadPerson(id) }
}
