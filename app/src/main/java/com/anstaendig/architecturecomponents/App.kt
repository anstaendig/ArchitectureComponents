package com.anstaendig.architecturecomponents

import android.app.Activity
import android.app.Application
import com.anstaendig.architecturecomponents.injection.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

  @Inject
  lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    AppInjector.init(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
