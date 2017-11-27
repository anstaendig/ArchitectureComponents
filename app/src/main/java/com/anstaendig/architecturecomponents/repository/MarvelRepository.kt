package com.anstaendig.architecturecomponents.repository

import com.anstaendig.architecturecomponents.datasource.MarvelError
import com.anstaendig.architecturecomponents.datasource.MarvelResultWrapper
import com.anstaendig.architecturecomponents.base.repository.BaseAction
import com.anstaendig.architecturecomponents.base.repository.BaseRepository
import com.anstaendig.architecturecomponents.base.repository.BaseResult
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

interface MarvelRepository : BaseRepository {

    fun getCharacterById(id: Int): Observable<GetCharacterResult>

    override val results: ObservableTransformer<MarvelAction, MarvelResult>
}

sealed class MarvelResult : BaseResult {
    object InProgress : MarvelResult()
    data class Success<out T>(val result: MarvelResultWrapper<T>) : MarvelResult()
    data class Failure(val error: MarvelError) : MarvelResult()
}

sealed class MarvelAction : BaseAction {
    data class LoadCharacterById(val id: Int) : MarvelAction()
}
