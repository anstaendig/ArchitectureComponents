package com.anstaendig.architecturecomponents.data.people.repository

import com.anstaendig.architecturecomponents.data.people.model.PersonDataModel
import io.reactivex.Single

interface PeopleRemote {

  fun getPeople(): Single<List<PersonDataModel>>

  fun getPeopleNextPage(url: String)

  fun searchPeople(name: String)

  fun getPersonById(id: String)
}
