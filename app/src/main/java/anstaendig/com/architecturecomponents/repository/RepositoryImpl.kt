package anstaendig.com.architecturecomponents.repository

import android.util.Log
import anstaendig.com.architecturecomponents.datasource.PersonDAO
import anstaendig.com.architecturecomponents.datasource.SwapiService
import anstaendig.com.architecturecomponents.datasource.toPerson
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl
@Inject
constructor(private val swapiService: SwapiService,
            private val personDAO: PersonDAO) : Repository {

  private val loadPerson: ObservableTransformer<Action.LoadPerson, Result> = ObservableTransformer { actions ->
    actions
        .flatMap { (id) ->
          Observable.merge(
              personDAO.loadById(id)
                  .toObservable(),
              swapiService.loadPerson(id)
                  .doOnSuccess({ personDAO.insert(it.apply { this.id = id }) })
                  .toObservable()
          ).map<Result> { personData -> Result.Success(personData.toPerson()) }
              .distinctUntilChanged()
              .doOnNext({ Log.d("TAG", "STOP $it") })
              .subscribeOn(Schedulers.io())
              .startWith(Result.InProgress)
              .onErrorReturn { e -> Result.Failure(e.message!!) }
        }.observeOn(AndroidSchedulers.mainThread())
  }

  private val loadLuke: ObservableTransformer<Action.LoadLuke, Result> = ObservableTransformer { actions ->
    actions
        .flatMap { _ ->
          swapiService
              .loadPerson("1")
              .subscribeOn(Schedulers.io())
              .toObservable()
              .map<Result> { personData -> Result.Success(personData.toPerson()) }
              .onErrorReturn { e -> Result.Failure(e.message!!) }
              .startWith(Result.InProgress)
        }
        .observeOn(AndroidSchedulers.mainThread())
  }

  override val results: ObservableTransformer<Action, Result> = ObservableTransformer { events ->
    events.publish { shared ->
      Observable.merge(
          shared.ofType(Action.LoadPerson::class.java).compose(loadPerson),
          shared.ofType(Action.LoadLuke::class.java).compose(loadLuke)
      )
    }
  }
}
