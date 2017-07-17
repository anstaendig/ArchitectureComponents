package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.ui.ExampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeExampleFragment(): ExampleFragment
}
