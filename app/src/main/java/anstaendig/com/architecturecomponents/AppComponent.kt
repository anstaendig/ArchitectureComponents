package anstaendig.com.architecturecomponents

import anstaendig.com.architecturecomponents.datasource.DatasourceModule
import anstaendig.com.architecturecomponents.repository.RepositoryModule
import anstaendig.com.architecturecomponents.ui.MainActivityModule
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
