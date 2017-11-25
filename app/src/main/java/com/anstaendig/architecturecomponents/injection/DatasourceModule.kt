package com.anstaendig.architecturecomponents.injection

import dagger.Module
import dagger.Provides

@Module
object DatasourceModule {

  @Provides
  @JvmStatic
  fun providePersonDAO(database: Database) = database.personDAO()
}
