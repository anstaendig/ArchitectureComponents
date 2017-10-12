package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.datasource.PersonDAO
import anstaendig.com.architecturecomponents.datasource.SwapiService
import anstaendig.com.architecturecomponents.repository.SWRepositoryImpl
import anstaendig.com.architecturecomponents.repository.SWRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
            swapiService: SwapiService,
            personDAO: PersonDAO
    ): SWRepository = SWRepositoryImpl(swapiService, personDAO)
}
