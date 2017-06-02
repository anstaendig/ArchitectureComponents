package anstaendig.com.architecturecomponents.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.TextView
import anstaendig.com.architecturecomponents.R
import anstaendig.com.architecturecomponents.ui.base.BaseActivity
import anstaendig.com.architecturecomponents.util.bindView
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

  @Inject
  lateinit var viewModel: MainActivityViewModel

  private val messageTextView: TextView by bindView(R.id.message)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.viewState.observe(this, Observer<MainActivityViewState> {
      it?.let { state -> render(state) }
    })
  }

  override fun getLayoutResource() = R.layout.activity_main

  private fun render(viewState: MainActivityViewState) {
    when(viewState) {
      is MainActivityViewState.Error -> messageTextView.text = viewState.errorMessage
      is MainActivityViewState.Success -> messageTextView.text = viewState.personData.name
      is MainActivityViewState.Loading -> messageTextView.text = "Is loading..."
    }
  }
}
