package com.anstaendig.architecturecomponents.persistence.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.anstaendig.architecturecomponents.persistence.people.dao.PersonDao
import com.anstaendig.architecturecomponents.persistence.people.model.PersonData
import javax.inject.Inject

@Database(entities = arrayOf(PersonData::class), version = 1)
abstract class SwapiDatabase
@Inject constructor() : RoomDatabase() {

  abstract fun personDao(): PersonDao
}


