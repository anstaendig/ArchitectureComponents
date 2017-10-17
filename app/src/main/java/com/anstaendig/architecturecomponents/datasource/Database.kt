package com.anstaendig.architecturecomponents.datasource

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(PersonData::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun personDAO(): PersonDAO
}


