package anstaendig.com.architecturecomponents.repository

import anstaendig.com.architecturecomponents.entities.Person
import io.reactivex.ObservableTransformer

interface Repository {
  val results: ObservableTransformer<Action, Result>
}

sealed class Result {
  object InProgress : Result()
  data class Success(val data: Person) : Result()
  data class Failure(val e: String) : Result()
}

sealed class Action {
  data class LoadPerson(val id: String) : Action()
  object LoadLuke : Action()
}
