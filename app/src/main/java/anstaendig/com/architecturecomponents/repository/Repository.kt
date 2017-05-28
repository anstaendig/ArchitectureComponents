package anstaendig.com.architecturecomponents.repository

import android.net.Uri
import anstaendig.com.architecturecomponents.datasource.PageData
import anstaendig.com.architecturecomponents.datasource.PersonData
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Repository {

  fun loadPeople(): Single<PageData<PersonData>>

  fun loadPeople(@Url url: Uri): Single<PageData<PersonData>>

  fun searchPeople(@Query("search") name: String): Single<PageData<PersonData>>

  fun loadPerson(@Path("id") id: String): Single<PersonData>

  fun addPersonToFavorites(personData: PersonData): Completable
}
