package anstaendig.com.architecturecomponents.repository

import anstaendig.com.architecturecomponents.datasource.SwapiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides @Singleton
  fun provideRepository(swapiService: SwapiService): Repository = RepositoryImpl(swapiService)
}
