package com.anstaendig.architecturecomponents.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.anstaendig.architecturecomponents.R
import com.anstaendig.architecturecomponents.util.bindView
import com.anstaendig.architecturecomponents.viewmodel.MainActivityViewModel
import com.anstaendig.architecturecomponents.base.ui.BaseActivity
import com.anstaendig.architecturecomponents.base.ui.event.UiEvent
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable

class MainActivity : BaseActivity<MainActivityViewModel, MainActivityViewState, MainActivityUiEvent>() {

  private val messageTextView: TextView by bindView(R.id.textview_message)
  private val editTextView: EditText by bindView(R.id.edittext_search)
  private val searchButtonView: Button by bindView(R.id.search_button)

  override val layoutResource = R.layout.a_main
  override val viewModelClass = MainActivityViewModel::class.java

  private val changeTextObservable: Observable<MainActivityUiEvent.OnTextChange>
      by lazy(LazyThreadSafetyMode.NONE) {
        editTextView.textChanges()
            .filter { text -> text.isNotEmpty() }
            .map { MainActivityUiEvent.OnTextChange(it.toString()) }
      }

  private val messageClickObservable: Observable<MainActivityUiEvent.OnMessageClick>
      by lazy(LazyThreadSafetyMode.NONE) {
        messageTextView.clicks()
            .map { MainActivityUiEvent.OnMessageClick(messageTextView.text.toString()) }
      }

  override val events: Observable<MainActivityUiEvent>
      by lazy(LazyThreadSafetyMode.NONE) {
        Observable.merge(changeTextObservable, messageClickObservable)
      }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (savedInstanceState == null)
      viewModel.events.accept(MainActivityUiEvent.OnInitialised("id"))
  }

  override fun render(viewState: MainActivityViewState) {
    when (viewState) {
      is MainActivityViewState.Idle -> messageTextView.text = "Type to search for a character"
      is MainActivityViewState.Failure -> messageTextView.text = viewState.errorMessage
      is MainActivityViewState.Success -> messageTextView.text = viewState.personData.name
      is MainActivityViewState.InProgress -> messageTextView.text = "Is loading..."
    }

  }
}

sealed class MainActivityUiEvent : UiEvent() {
  data class OnTextChange(val text: String) : MainActivityUiEvent()
  data class OnMessageClick(val text: String) : MainActivityUiEvent()
  data class OnInitialised(val id: String) : MainActivityUiEvent()
}
