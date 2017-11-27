package com.anstaendig.architecturecomponents.injection

import com.anstaendig.architecturecomponents.App
import dagger.Module
import dagger.Provides

@Module
abstract class PersistenceModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideSwapiDatabase(context: App) = Database
    }
}
