package com.anstaendig.architecturecomponents.injection

import com.anstaendig.architecturecomponents.BuildConfig
import com.anstaendig.architecturecomponents.remote.service.SwapiService
import com.anstaendig.architecturecomponents.remote.service.SwapiServiceFactory
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

  @Module
  companion object {

    @Provides
    @JvmStatic
    fun provideSwapiService(): SwapiService = SwapiServiceFactory.getService(BuildConfig.DEBUG)
  }
}
