package anstaendig.com.architecturecomponents.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import anstaendig.com.architecturecomponents.R
import anstaendig.com.architecturecomponents.bindView
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

  val messageTextView: TextView by bindView(R.id.message)

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    messageTextView.text = "whatever"
  }
}
