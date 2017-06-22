package anstaendig.com.architecturecomponents.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import anstaendig.com.architecturecomponents.R
import anstaendig.com.architecturecomponents.ui.base.BaseActivity
import anstaendig.com.architecturecomponents.util.bindView
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable

class MainActivity : BaseActivity<MainActivityViewModel, MainActivityViewState>() {

  private val messageTextView: TextView by bindView(R.id.message)
  private val editTextView: EditText by bindView(R.id.edit)

  private val changeTextObservable: Observable<UiEvent.OnTextChange> by lazy {
    editTextView.textChanges()
        .filter { text -> text.isNotBlank() }
        .map { UiEvent.OnTextChange(it.toString()) }
  }

  private val messageClickObservable: Observable<UiEvent.OnMessageClick> by lazy {
    messageTextView.clicks()
        .map { UiEvent.OnMessageClick(messageTextView.text.toString()) }
  }

  private val events: Observable<UiEvent> by lazy {
    Observable.merge(changeTextObservable, messageClickObservable)
  }

  override val layoutResource = R.layout.activity_main
  override val viewModelClass = MainActivityViewModel::class.java

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    disposables.addAll(events.subscribe { event ->
      viewModel.events.onNext(event)
    })
    viewModel.events.onNext(UiEvent.OnInitialised("id"))
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

sealed class UiEvent {
  data class OnTextChange(val text: String) : UiEvent()
  data class OnMessageClick(val text: String) : UiEvent()
  data class OnInitialised(val id: String) : UiEvent()
}
