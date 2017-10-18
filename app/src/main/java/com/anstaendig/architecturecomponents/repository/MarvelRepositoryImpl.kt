package com.anstaendig.architecturecomponents.repository

import com.anstaendig.architecturecomponents.datasource.CharacterData
import com.anstaendig.architecturecomponents.datasource.MarvelError
import com.anstaendig.architecturecomponents.datasource.MarvelResultWrapper
import com.anstaendig.architecturecomponents.datasource.MarvelService
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

sealed class GetCharacterResult {
    data class Success(val character: CharacterData) : GetCharacterResult()
    sealed class Failure : GetCharacterResult() {
        object NotFound : Failure()
        object NetworkError : Failure()
    }
}

fun MarvelResultWrapper<CharacterData>.toCharacterResult(): GetCharacterResult =
        data?.results?.get(0)?.let {
            GetCharacterResult.Success(it)
        } ?: GetCharacterResult.Failure.NotFound

class MarvelRepositoryImpl
@Inject
constructor(
        private val marvelService: MarvelService
) : MarvelRepository {

    override fun getCharacterById(id: Int): Observable<GetCharacterResult> =
            marvelService.fetchCharacterById(id)
                    .toObservable()
                    .map { it.toCharacterResult() }
                    .onErrorReturn { GetCharacterResult.Failure.NetworkError }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

    private val loadCharacterById: ObservableTransformer<MarvelAction.LoadCharacterById, MarvelResult> =
            ObservableTransformer { actions ->
                actions.switchMap {
                    marvelService.fetchCharacterById(it.id)
                            .subscribeOn(Schedulers.io())
                            .toObservable()
                            .map<MarvelResult> { characterData ->
                                MarvelResult.Success(characterData)
                            }
                            .onErrorReturn { throwable ->
                                MarvelResult.Failure(MarvelError(400, throwable.message!!))
                            }
                            .startWith(MarvelResult.InProgress)
                }.observeOn(AndroidSchedulers.mainThread())
            }

    override val results: ObservableTransformer<MarvelAction, MarvelResult> =
            ObservableTransformer { events ->
                events.publish { shared ->
                    shared.ofType(MarvelAction.LoadCharacterById::class.java).compose(loadCharacterById)
                }
            }
}
