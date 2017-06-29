package anstaendig.com.architecturecomponents.repository

import anstaendig.com.architecturecomponents.datasource.PersonData
import io.reactivex.ObservableTransformer

interface Repository {
  val results: ObservableTransformer<Action, Result>
}

sealed class Result {
  object InProgress : Result()
  data class Success(val data: PersonData) : Result()
  data class Failure(val e: String) : Result()
}

sealed class Action {
  data class LoadPerson(val id: String) : Action()
  object LoadLuke : Action()
}
