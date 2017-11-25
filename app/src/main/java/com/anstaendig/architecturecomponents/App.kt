package com.anstaendig.architecturecomponents

import com.anstaendig.architecturecomponents.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out @JvmSuppressWildcards DaggerApplication> {
    return DaggerAppComponent.builder().create(this)
  }
}
