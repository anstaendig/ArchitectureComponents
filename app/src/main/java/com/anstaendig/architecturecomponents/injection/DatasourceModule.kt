package com.anstaendig.architecturecomponents.injection

import android.arch.persistence.room.BuildConfig
import android.arch.persistence.room.Room
import android.content.Context
import com.anstaendig.architecturecomponents.datasource.Database
import com.anstaendig.architecturecomponents.datasource.MarvelService
import com.anstaendig.architecturecomponents.datasource.SwapiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
object DatasourceModule {

    @Provides
    @JvmStatic
    fun provideMarvelService(
            okHttpClient: OkHttpClient,
            @Named("MarvelBaseUrl") baseUrl: String
    ): MarvelService =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build()
                    .create(MarvelService::class.java)

    @Provides
    @JvmStatic
    fun provideSwapiService(
            okHttpClient: OkHttpClient,
            @Named("BaseUrl") baseUrl: String
    ): SwapiService =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .build()
                    .create(SwapiService::class.java)

    @Provides
    @JvmStatic
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            }.build()

    @Provides
    @JvmStatic
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @JvmStatic
    fun provideDatabase(context: Context): Database =
            Room.databaseBuilder(context, Database::class.java, "database").build()

    @Provides
    @JvmStatic
    fun providePersonDAO(database: Database) = database.personDAO()

    @Provides
    @JvmStatic
    @Named("BaseUrl")
    fun provideBaseUrl() = "http://swapi.co/api/"

    @Provides
    @JvmStatic
    @Named("MarvelBaseUrl")
    fun provideMarvelBaseUrl() = "http://gateway.marvel.com/"
}
