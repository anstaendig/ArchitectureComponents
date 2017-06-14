package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.datasource.SwapiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatasourceModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    val okHttpClient = OkHttpClient.Builder()
    return okHttpClient.build()
  }

  @Provides
  @Singleton
  fun provideSwapiService(okHttpClient: OkHttpClient,
                          @Named("BaseUrl") baseUrl: String): SwapiService {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
        .create(SwapiService::class.java)
  }

  @Provides
  @Named("BaseUrl")
  fun proveBaseUrl() = "http://swapi.co/api/"
}
