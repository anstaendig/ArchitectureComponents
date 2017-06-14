package anstaendig.com.architecturecomponents.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import anstaendig.com.architecturecomponents.R
import anstaendig.com.architecturecomponents.ui.base.BaseActivity
import anstaendig.com.architecturecomponents.util.bindView
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import com.jakewharton.rxbinding2.widget.textChanges

class MainActivity : BaseActivity<MainActivityViewModel, MainActivityViewState>() {

  private val messageTextView: TextView by bindView(R.id.message)
  private val editTextView: EditText by bindView(R.id.edit)

  override val layoutResource = R.layout.activity_main

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    disposables.add(editTextView.textChanges()
        .filter { text -> text.isNotBlank() }
        .map { UiEvent.OnTextChange(it.toString()) }
        .subscribe({ action ->
          viewModel.subject.onNext(action)
        })
    )
  }

  override fun render(viewState: MainActivityViewState) {
    when (viewState) {
      is MainActivityViewState.Error -> messageTextView.text = viewState.errorMessage
      is MainActivityViewState.Success -> messageTextView.text = viewState.personData.name
      is MainActivityViewState.Loading -> messageTextView.text = "Is loading..."
    }
  }
}

sealed class UiEvent {
  data class OnTextChange(val text: String) : UiEvent()
}
