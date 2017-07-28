package anstaendig.com.architecturecomponents.repository

import anstaendig.com.architecturecomponents.datasource.PersonDAO
import anstaendig.com.architecturecomponents.datasource.SwapiService
import anstaendig.com.architecturecomponents.datasource.toPerson
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SWRepositoryImpl
@Inject
constructor(private val swapiService: SwapiService,
            private val personDAO: PersonDAO) : SWRepository {

  private val loadPerson: ObservableTransformer<SWAction.LoadPerson, SWResult> = ObservableTransformer { actions ->
    actions
        .flatMap { (id) ->
          Observable.merge(
              personDAO.loadById(id)
                  .toObservable(),
              swapiService.loadPerson(id)
                  .doOnSuccess({ personDAO.insert(it.apply { this.id = id }) })
                  .toObservable()
          ).map<SWResult> { personData -> SWResult.Success(personData.toPerson()) }
              .distinctUntilChanged()
              .subscribeOn(Schedulers.io())
              .startWith(SWResult.InProgress)
              .onErrorReturn { e -> SWResult.Failure(e.message!!) }
        }.observeOn(AndroidSchedulers.mainThread())
  }

  private val loadLuke: ObservableTransformer<SWAction.LoadLuke, SWResult> = ObservableTransformer { actions ->
    actions
        .flatMap { _ ->
          swapiService
              .loadPerson("1")
              .subscribeOn(Schedulers.io())
              .toObservable()
              .map<SWResult> { personData -> SWResult.Success(personData.toPerson()) }
              .onErrorReturn { e -> SWResult.Failure(e.message!!) }
              .startWith(SWResult.InProgress)
        }
        .observeOn(AndroidSchedulers.mainThread())
  }

  override val results: ObservableTransformer<SWAction, SWResult> = ObservableTransformer { events ->
    events.publish { shared ->
      Observable.merge(
          shared.ofType(SWAction.LoadPerson::class.java).compose(loadPerson),
          shared.ofType(SWAction.LoadLuke::class.java).compose(loadLuke)
      )
    }
  }
}
