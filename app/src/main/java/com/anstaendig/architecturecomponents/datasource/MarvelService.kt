package com.anstaendig.architecturecomponents.datasource

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MarvelService {

    // CHARACTERS

    /**
     * Fetches lists of comic characters with optional filters.
     *
     * @param[name] Return only characters matching the specified full character name (e.g.
     * Spider-Man).
     * @param[nameStartsWith] Return characters with names that begin with the specified string
     * (e.g. Sp).
     * @param[modifiedSince] Return only characters which have been modified since the specified
     * date.
     * @param[comics]  Return only characters which appear in the specified comics (accepts a
     * comma-separated list of ids).
     * @param[series] Return only characters which appear the specified series (accepts a
     * comma-separated list of ids).
     * @param[events] Return only characters which appear in the specified events (accepts a
     * comma-separated list of ids).
     * @param[stories] Return only characters which appear the specified stories (accepts a
     * comma-separated list of ids).
     * @param[orderBy] Order the result set by a field or fields. Add a "-" to the value sort in
     * descending order. Multiple values are given priority in the order in which they are passed.
     * @param[limit] Limit the result set to the specified number of resources.
     * @param[offset] Skip the specified number of resources in the result set.
     * @return [Single] that emits [MarvelResultWrapper] containing [List] of [CharacterData]
     */
    @GET("v1/public/characters")
    fun fetchCharacters(
            @Query("name") name: String?,
            @Query("nameStartsWith") nameStartsWith: String?,
            @Query("modifiedSince") modifiedSince: Date?,
            @Query("comics") comics: Int?,
            @Query("series") series: Int?,
            @Query("events") events: Int?,
            @Query("stories") stories: Int?,
            @Query("orderBy") orderBy: String?,
            @Query("limit") limit: Int?,
            @Query("offset") offset: Int?
    ): Single<MarvelResultWrapper<CharacterData>>

    /**
     * This method fetches a single character resource. It is the canonical URI for any character
     * resource provided by the API.
     *
     * @param[characterId] A single character id.
     * @return [Single] that emits [MarvelResultWrapper] containing [List] of [CharacterData]
     */
    @GET("v1/public/characters/{characterId]")
    fun fetchCharacterById(
            @Path("characterId") characterId: Int
    ): Single<MarvelResultWrapper<CharacterData>>
}