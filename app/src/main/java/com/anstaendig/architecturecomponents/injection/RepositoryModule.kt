package com.anstaendig.architecturecomponents.injection

import com.anstaendig.architecturecomponents.repository.MarvelRepository
import com.anstaendig.architecturecomponents.repository.MarvelRepositoryImpl
import com.anstaendig.architecturecomponents.repository.SWRepository
import com.anstaendig.architecturecomponents.repository.SWRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindSwapiRepository(swRepositoryImpl: SWRepositoryImpl): SWRepository

    @Binds
    abstract fun bindMarvelRepository(marvelRepositoryImpl: MarvelRepositoryImpl): MarvelRepository
}
