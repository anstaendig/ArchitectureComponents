package anstaendig.com.architecturecomponents.ui

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(
    MainActivitySubComponent::class
))
abstract class MainActivityModule {
  @Binds
  @IntoMap
  @ActivityKey(MainActivity::class)
  internal abstract fun bindMainActivityInjectorFactory(builder: MainActivitySubComponent.Builder)
      : AndroidInjector.Factory<out Activity>
}
