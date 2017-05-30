package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

  @ContributesAndroidInjector
  abstract fun contributesMainActivity(): MainActivity
}
