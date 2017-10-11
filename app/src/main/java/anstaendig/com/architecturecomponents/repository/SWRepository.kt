package anstaendig.com.architecturecomponents.repository

import anstaendig.com.architecturecomponents.entities.Person
import com.anstaendig.base.repository.BaseAction
import com.anstaendig.base.repository.BaseRepository
import com.anstaendig.base.repository.BaseResult
import io.reactivex.ObservableTransformer

interface SWRepository : BaseRepository {
    override val results: ObservableTransformer<SWAction, SWResult>
}

sealed class SWResult : BaseResult {
    object InProgress : SWResult()
    data class Success(val data: Person) : SWResult()
    data class Failure(val e: String) : SWResult()
}

sealed class SWAction : BaseAction {
    data class LoadPerson(val id: String) : SWAction()
    object LoadLuke : SWAction()
}

