package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.datasource.SwapiService
import anstaendig.com.architecturecomponents.repository.Repository
import anstaendig.com.architecturecomponents.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides
  @Singleton
  fun provideRepository(swapiService: SwapiService): Repository = RepositoryImpl(swapiService)
}
