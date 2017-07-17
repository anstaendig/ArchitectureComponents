package anstaendig.com.architecturecomponents.injection

import android.arch.persistence.room.BuildConfig
import android.arch.persistence.room.Room
import android.content.Context
import anstaendig.com.architecturecomponents.datasource.Database
import anstaendig.com.architecturecomponents.datasource.SwapiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class DatasourceModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder().apply {
      if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
    }.build()
  }

  @Provides
  @Singleton
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor
      = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

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
  @Singleton
  fun provideDatabase(context: Context): Database
      = Room.databaseBuilder(context, Database::class.java, "database").build()

  @Provides
  @Singleton
  fun providePersonDAO(database: Database) = database.personDAO()

  @Provides
  @Named("BaseUrl")
  fun proveBaseUrl() = "http://swapi.co/api/"
}
