package com.anstaendig.architecturecomponents.remote.service

import com.anstaendig.architecturecomponents.remote.people.model.PersonModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SwapiService {

  @GET("people/")
  fun getPeople(): Single<PageModel<PersonModel>>

  @GET
  fun getPeopleNextPage(@Url url: String): Single<PageModel<PersonModel>>

  @GET("people/")
  fun searchPeople(@Query("search") name: String): Single<PageModel<PersonModel>>

  @GET("people/{id}")
  fun getPersonById(@Path("id") id: String): Single<PersonModel>
}
