package anstaendig.com.architecturecomponents.ui

import anstaendig.com.architecturecomponents.datasource.PersonData
import anstaendig.com.architecturecomponents.ui.base.BaseViewState

sealed class MainActivityViewState : BaseViewState {

  object Loading : MainActivityViewState()
  data class Success(val personData: PersonData) : MainActivityViewState()
  data class Error(val errorMessage: String) : MainActivityViewState()
}


