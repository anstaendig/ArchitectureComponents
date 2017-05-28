package anstaendig.com.architecturecomponents

import android.app.Application
import anstaendig.com.architecturecomponents.injection.*

class App : Application() {

  val appComponent: AppComponent by lazy {
    DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .datasourceModule(DatasourceModule())
        .repositoryModule(RepositoryModule())
        .build()
  }

  override fun onCreate() {
    super.onCreate()
    appComponent.injectTo(this)
  }
}
