package com.anstaendig.architecturecomponents.injection

import android.content.Context
import com.anstaendig.architecturecomponents.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

  @Binds
  abstract fun bindContext(application: App): Context
}
