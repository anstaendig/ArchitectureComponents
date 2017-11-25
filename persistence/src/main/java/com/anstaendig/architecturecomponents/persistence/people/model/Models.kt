package com.anstaendig.architecturecomponents.persistence.people.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "people")
data class PersonData(
    @PrimaryKey val id: String?,
    val name: String,
    val birthYear: String?,
    val eyeColor: String?,
    val gender: String?,
    val hairColor: String?,
    val height: String?,
    val mass: String?,
    val skinColor: String?,
    val homeworld: String?,
    val films: List<String>?,
    val species: List<String>?,
    val starships: List<String>?,
    val vehicles: List<String>?,
    val url: String?,
    val created: String?,
    val edited: String?
)
