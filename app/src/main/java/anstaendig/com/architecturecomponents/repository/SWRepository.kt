package anstaendig.com.architecturecomponents.repository

import anstaendig.com.architecturecomponents.entities.Person
import com.anstaendig.base.repository.BaseAction
import com.anstaendig.base.repository.BaseRepository
import com.anstaendig.base.repository.BaseResult
import io.reactivex.ObservableTransformer

interface SWRepository: BaseRepository {
  override val results: ObservableTransformer<Action, Result>
}

sealed class Result : BaseResult {
  object InProgress : Result()
  data class Success(val data: Person) : Result()
  data class Failure(val e: String) : Result()
}

sealed class Action : BaseAction {
  data class LoadPerson(val id: String) : Action()
  object LoadLuke : Action()
}

