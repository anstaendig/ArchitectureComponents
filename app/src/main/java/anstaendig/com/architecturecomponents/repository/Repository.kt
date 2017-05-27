package anstaendig.com.architecturecomponents.repository

import android.arch.lifecycle.LiveData
import android.net.Uri
import anstaendig.com.architecturecomponents.datasource.PageData
import anstaendig.com.architecturecomponents.datasource.PersonData
import io.reactivex.Single
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Repository {

  fun loadPeople(): Single<PageData<PersonData>>

  fun loadPeople(@Url url: Uri): LiveData<PageData<PersonData>>

  fun searchPeople(@Query("search") name: String): LiveData<PageData<PersonData>>

  fun loadPerson(@Path("id") id: String): LiveData<PersonData>
}
