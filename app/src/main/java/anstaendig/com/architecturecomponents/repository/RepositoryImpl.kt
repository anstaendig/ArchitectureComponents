package anstaendig.com.architecturecomponents.repository

import android.net.Uri
import anstaendig.com.architecturecomponents.datasource.PageData
import anstaendig.com.architecturecomponents.datasource.PersonData
import anstaendig.com.architecturecomponents.datasource.SwapiService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl
@Inject
constructor(private val swapiService: SwapiService) : Repository {

  override fun loadPeople(): Single<PageData<PersonData>>
      = swapiService.loadPeople().subscribeOn(Schedulers.io())

  override fun loadPeople(url: Uri): Single<PageData<PersonData>>
      = swapiService.loadPeople(url).subscribeOn(Schedulers.io())

  override fun searchPeople(name: String): Single<PageData<PersonData>>
      = swapiService.searchPeople(name).subscribeOn(Schedulers.io())

  override fun loadPerson(id: String): Single<PersonData>
      = swapiService.loadPerson(id).subscribeOn(Schedulers.io())

  override fun addPersonToFavorites(personData: PersonData): Completable {
    TODO()
  }

  private val loadPerson: ObservableTransformer<Action.LoadPerson, Result> = ObservableTransformer { actions ->
    actions
        .flatMap { (id) ->
          swapiService
              .loadPerson(id)
              .subscribeOn(Schedulers.io())
              .toObservable()
        }
        .map<Result> { personData -> Result.Success(personData) }
        .onErrorReturn { e -> Result.Failure(e.message!!) }
        .observeOn(AndroidSchedulers.mainThread())
        .startWith(Result.InProgress)
  }

  private val actionTwoResult: ObservableTransformer<Action.Two, Result> = ObservableTransformer { actions ->
    actions
        .flatMap { _ -> loadPerson("1").toObservable() }
        .map<Result> { personData -> Result.Success(personData) }
        .onErrorReturn { e -> Result.Failure(e.message!!) }
        .observeOn(AndroidSchedulers.mainThread())
        .startWith(Result.InProgress)
  }

  override val results: ObservableTransformer<Action, Result> = ObservableTransformer { events ->
    events.publish { shared ->
      Observable.merge(
          shared.ofType(Action.LoadPerson::class.java).compose(loadPerson),
          shared.ofType(Action.Two::class.java).compose(actionTwoResult)
      )
    }
  }
}
