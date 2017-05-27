package anstaendig.com.architecturecomponents.datasource

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DatasourceModule {

  @Provides @Singleton
  fun provideSwapiService(): SwapiService {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("http://swapi.co/api/")
        .build()
        .create(SwapiService::class.java)
  }
}
