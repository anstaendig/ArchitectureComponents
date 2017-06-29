package anstaendig.com.architecturecomponents.ui

import anstaendig.com.architecturecomponents.datasource.PersonData
import anstaendig.com.architecturecomponents.ui.base.BaseViewState

sealed class MainActivityViewState : BaseViewState {
  object Idle : MainActivityViewState()
  object InProgress : MainActivityViewState()
  data class Success(val personData: PersonData) : MainActivityViewState()
  data class Failure(val errorMessage: String) : MainActivityViewState()
}
