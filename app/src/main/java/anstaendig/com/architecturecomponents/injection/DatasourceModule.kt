package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.datasource.SwapiService
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DatasourceModule {

  @Provides @Singleton
  fun provideSwapiService(): SwapiService {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("http://swapi.co/api/")
        .build()
        .create(SwapiService::class.java)
  }
}
