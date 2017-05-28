package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    RepositoryModule::class,
    DatasourceModule::class
))
interface AppComponent {

  fun injectTo(app: App)

  fun plus(module: MainActivityModule): MainActivitySubComponent
}
