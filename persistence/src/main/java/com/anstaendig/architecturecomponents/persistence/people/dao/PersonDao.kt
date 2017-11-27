package com.anstaendig.architecturecomponents.persistence.people.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.anstaendig.architecturecomponents.persistence.people.QUERY_PERSON_BY_ID
import com.anstaendig.architecturecomponents.persistence.people.model.PersonPersistenceModel
import io.reactivex.Flowable

@Dao
interface PersonDao {

  @Query(QUERY_PERSON_BY_ID)
  fun getPersonById(personId: String): Flowable<PersonPersistenceModel>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPerson(person: PersonPersistenceModel)
}
