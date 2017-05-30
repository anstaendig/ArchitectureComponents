package anstaendig.com.architecturecomponents.injection

import android.app.Application
import anstaendig.com.architecturecomponents.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    AndroidInjectionModule::class,
    ActivityBuilderModule::class,
    RepositoryModule::class,
    DatasourceModule::class
))
interface AppComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(app: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: App)
}
