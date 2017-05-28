package anstaendig.com.architecturecomponents.ui

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import anstaendig.com.architecturecomponents.App
import anstaendig.com.architecturecomponents.R
import anstaendig.com.architecturecomponents.injection.MainActivityModule
import anstaendig.com.architecturecomponents.util.bindView
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

  val viewModel: MainActivityViewModel by lazy {
    ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
  }

  private val messageTextView: TextView by bindView(R.id.message)

  // We have to hold a strong reference to the instance of LifecycleRegistry, otherwise GC will
  // collect it and LifecycleObserver of LiveData won’t see the active observers from LifecycleOwner
  // and our views won’t receive the updates.
  private val lifecycleRegistry = LifecycleRegistry(this)

  override fun getLifecycle() = lifecycleRegistry

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    (application as App).appComponent.plus(MainActivityModule()).inject(this)
    viewModel.viewState.observe(this, Observer<MainActivityViewState> {
      it?.let { messageTextView.text = it.name }
    })
  }
}
