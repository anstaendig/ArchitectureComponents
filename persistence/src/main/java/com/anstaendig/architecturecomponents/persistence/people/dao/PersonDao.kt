package com.anstaendig.architecturecomponents.persistence.people.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.anstaendig.architecturecomponents.persistence.people.model.PersonData
import io.reactivex.Flowable

@Dao
interface PersonDao {

  @Query("SELECT * FROM persons WHERE id LIKE :personId")
  fun getPersonById(personId: String): Flowable<PersonData>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPerson(person: PersonData)
}
