package com.anstaendig.architecturecomponents.persistence.database

import android.arch.persistence.room.Room
import android.content.Context
import javax.inject.Singleton

object SwapiDatabaseFactory {

    @Singleton
    fun getDatabase(context: Context): SwapiDatabase {
        return Room.databaseBuilder(context, SwapiDatabase::class.java, "swapi-database").build()
    }
}
