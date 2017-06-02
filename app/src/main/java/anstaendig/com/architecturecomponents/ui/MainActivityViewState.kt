package anstaendig.com.architecturecomponents.ui

import anstaendig.com.architecturecomponents.datasource.PersonData

sealed class MainActivityViewState {

  object Loading : MainActivityViewState()
  class Success(val personData: PersonData) : MainActivityViewState()
  class Error(val errorMessage: String) : MainActivityViewState()
}


