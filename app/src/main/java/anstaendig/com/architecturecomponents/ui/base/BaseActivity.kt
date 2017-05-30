package anstaendig.com.architecturecomponents.ui.base

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {

  // We have to hold a strong reference to the instance of LifecycleRegistry, otherwise GC will
  // collect it and LifecycleObserver of LiveData won’t see the active observers from LifecycleOwner
  // and our views won’t receive the updates.
  @Suppress("LeakingThis")
  private val lifecycleRegistry = LifecycleRegistry(this)

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(getLayoutResource())
  }

  override fun getLifecycle() = lifecycleRegistry

  @LayoutRes
  abstract fun getLayoutResource(): Int
}
