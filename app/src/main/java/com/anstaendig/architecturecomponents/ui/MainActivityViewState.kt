package com.anstaendig.architecturecomponents.ui

import com.anstaendig.architecturecomponents.entities.Person
import com.anstaendig.base.ui.BaseViewState

sealed class MainActivityViewState : BaseViewState {
    object Idle : MainActivityViewState()
    object InProgress : MainActivityViewState()
    data class Success(val personData: Person) : MainActivityViewState()
    data class Failure(val errorMessage: String) : MainActivityViewState()
}
