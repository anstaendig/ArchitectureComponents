package anstaendig.com.architecturecomponents.datasource

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DatasourceModule {

  @Provides @Singleton
  fun provideSwapiService(): SwapiService {
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(SwapiService::class.java)
  }
}
