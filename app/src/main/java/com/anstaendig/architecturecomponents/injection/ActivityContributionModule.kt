package com.anstaendig.architecturecomponents.injection

import com.anstaendig.architecturecomponents.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityContributionModule {

  @ContributesAndroidInjector(modules = arrayOf(FragmentContributionModule::class))
  abstract fun contributeMainActivity(): MainActivity
}
