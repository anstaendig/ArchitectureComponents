package com.anstaendig.architecturecomponents.datasource

import android.arch.persistence.room.TypeConverter
import java.util.*

class Converters {

    companion object {
        @JvmStatic
        @TypeConverter
        fun List<String>.convertToRoom() = Arrays.toString(arrayOf(this))

        @JvmStatic
        @TypeConverter
        fun String.convertToRoom() = Arrays.asList(this)
    }
}

