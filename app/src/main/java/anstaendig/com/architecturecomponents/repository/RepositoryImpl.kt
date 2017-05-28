package anstaendig.com.architecturecomponents.repository

import android.net.Uri
import anstaendig.com.architecturecomponents.datasource.PageData
import anstaendig.com.architecturecomponents.datasource.PersonData
import anstaendig.com.architecturecomponents.datasource.SwapiService
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl
@Inject
constructor(private val swapiService: SwapiService) : Repository {

  override fun loadPeople(): Single<PageData<PersonData>>
      = swapiService.loadPeople()

  override fun loadPeople(url: Uri): Single<PageData<PersonData>>
      = swapiService.loadPeople(url)

  override fun searchPeople(name: String): Single<PageData<PersonData>>
      = swapiService.searchPeople(name)

  override fun loadPerson(id: String): Single<PersonData>
      = swapiService.loadPerson(id)

  override fun addPersonToFavorites(personData: PersonData): Completable {
    return Completable.complete()
  }
}
