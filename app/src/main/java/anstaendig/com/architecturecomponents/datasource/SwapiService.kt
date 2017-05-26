package anstaendig.com.architecturecomponents.datasource

import android.net.Uri
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SwapiService {

  @GET("people/")
  fun loadPeople(): Single<PageData<PersonData>>

  @GET
  fun loadPeople(@Url url: Uri): Single<PageData<PersonData>>

  @GET("people/")
  fun searchPeople(@Query("search") name: String): Single<PageData<PersonData>>

  @GET("people/{id}")
  fun loadPerson(@Path("id") id: String): Single<PersonData>
}
