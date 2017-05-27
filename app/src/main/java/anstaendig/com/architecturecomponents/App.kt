package anstaendig.com.architecturecomponents

import android.app.Activity
import android.app.Application
import anstaendig.com.architecturecomponents.injection.*
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector
import javax.inject.Inject

class App : Application(), HasDispatchingActivityInjector {

  @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

  companion object {
    lateinit var graph: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    graph = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .datasourceModule(DatasourceModule())
        .repositoryModule(RepositoryModule())
        .build()
    graph.inject(this)
  }

  override fun activityInjector() = dispatchingActivityInjector
}
