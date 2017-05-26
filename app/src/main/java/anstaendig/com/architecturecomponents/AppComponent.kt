package anstaendig.com.architecturecomponents

import anstaendig.com.architecturecomponents.ui.MainActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = arrayOf(
    AppModule::class,
    AndroidInjectionModule::class,
    MainActivityModule::class
))
interface AppComponent {

  fun inject(app: App)
}
