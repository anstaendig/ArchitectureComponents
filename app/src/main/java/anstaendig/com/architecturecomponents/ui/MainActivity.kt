package anstaendig.com.architecturecomponents.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import anstaendig.com.architecturecomponents.R
import anstaendig.com.architecturecomponents.ui.base.BaseActivity
import anstaendig.com.architecturecomponents.ui.event.UiEvent
import anstaendig.com.architecturecomponents.util.bindView
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable

class MainActivity : BaseActivity<MainActivityViewModel, MainActivityViewState, MainActivityUiEvent>() {

  private val messageTextView: TextView by bindView(R.id.message)
  private val editTextView: EditText by bindView(R.id.edit)

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
      viewModel.events.onNext(MainActivityUiEvent.OnInitialised("id"))
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
