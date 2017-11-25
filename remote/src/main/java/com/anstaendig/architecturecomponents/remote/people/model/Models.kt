package com.anstaendig.architecturecomponents.remote.people.model

import com.squareup.moshi.Json

data class PersonRemoteModel(
    val id: String,
    val name: String,
    @Json(name = "birth_year") val birthYear: String,
    @Json(name = "eye_color") val eyeColor: String,
    val gender: String,
    @Json(name = "hair_color") val hairColor: String,
    val height: String,
    val mass: String,
    @Json(name = "skin_color") val skinColor: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val url: String,
    val created: String,
    val edited: String
)
