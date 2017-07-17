package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

  @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
  abstract fun contributesMainActivity(): MainActivity
}
