package anstaendig.com.architecturecomponents.ui

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import anstaendig.com.architecturecomponents.R
import anstaendig.com.architecturecomponents.util.bindView
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

  @Inject
  lateinit var viewModel: MainActivityViewModel

  private val messageTextView: TextView by bindView(R.id.message)

  // We have to hold a strong reference to the instance of LifecycleRegistry, otherwise GC will
  // collect it and LifecycleObserver of LiveData won’t see the active observers from LifecycleOwner
  // and our views won’t receive the updates.
  private val lifecycleRegistry = LifecycleRegistry(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    viewModel.viewState.observe(this, Observer<MainActivityViewState> {
      it?.let { state -> render(state) }
    })
  }

  override fun getLifecycle() = lifecycleRegistry

  private fun render(viewState: MainActivityViewState) {
    messageTextView.text = viewState.name
  }
}
