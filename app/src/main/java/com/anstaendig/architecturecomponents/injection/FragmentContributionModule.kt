package com.anstaendig.architecturecomponents.injection

import com.anstaendig.architecturecomponents.ui.ExampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentContributionModule {

  @ContributesAndroidInjector
  abstract fun contributeExampleFragment(): ExampleFragment
}
