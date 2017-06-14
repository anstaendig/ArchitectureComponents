package anstaendig.com.architecturecomponents.repository

import android.net.Uri
import anstaendig.com.architecturecomponents.datasource.PageData
import anstaendig.com.architecturecomponents.datasource.PersonData
import anstaendig.com.architecturecomponents.datasource.SwapiService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl
@Inject
constructor(private val swapiService: SwapiService) : Repository {

  override fun loadPeople(): Single<PageData<PersonData>>
      = swapiService.loadPeople().subscribeOn(Schedulers.io())

  override fun loadPeople(url: Uri): Single<PageData<PersonData>>
      = swapiService.loadPeople(url).subscribeOn(Schedulers.io())

  override fun searchPeople(name: String): Single<PageData<PersonData>>
      = swapiService.searchPeople(name).subscribeOn(Schedulers.io())

  override fun loadPerson(id: String): Single<PersonData>
      = swapiService.loadPerson(id).subscribeOn(Schedulers.io())

  override fun addPersonToFavorites(personData: PersonData): Completable {
    return Completable.complete()
  }
}
