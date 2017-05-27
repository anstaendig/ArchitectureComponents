package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.App
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    AndroidSupportInjectionModule::class,
    RepositoryModule::class,
    DatasourceModule::class,
    MainActivityModule::class
))
interface AppComponent {

  fun inject(app: App)
  fun inject(viewModel: MainActivityViewModel)
}
