package anstaendig.com.architecturecomponents

import android.app.Activity
import android.app.Application
import anstaendig.com.architecturecomponents.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

  @Inject
  lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    DaggerAppComponent
        .builder()
        .application(this)
        .build()
        .inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
