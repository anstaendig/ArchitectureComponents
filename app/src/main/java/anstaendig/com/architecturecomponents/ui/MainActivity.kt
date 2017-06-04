package anstaendig.com.architecturecomponents.ui

import android.widget.TextView
import anstaendig.com.architecturecomponents.R
import anstaendig.com.architecturecomponents.ui.base.BaseActivity
import anstaendig.com.architecturecomponents.util.bindView
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, MainActivityViewState>() {

  private val messageTextView: TextView by bindView(R.id.message)

  override val layoutResource = R.layout.activity_main

  override fun render(viewState: MainActivityViewState) {
    when (viewState) {
      is MainActivityViewState.Error -> messageTextView.text = viewState.errorMessage
      is MainActivityViewState.Success -> messageTextView.text = viewState.personData.name
      is MainActivityViewState.Loading -> messageTextView.text = "Is loading..."
    }
  }
}
