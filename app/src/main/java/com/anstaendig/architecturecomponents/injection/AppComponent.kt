package com.anstaendig.architecturecomponents.injection

import com.anstaendig.architecturecomponents.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ActivityContributionModule::class,
    FragmentContributionModule::class,
    RemoteModule::class
))
interface AppComponent {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<App>()
}

