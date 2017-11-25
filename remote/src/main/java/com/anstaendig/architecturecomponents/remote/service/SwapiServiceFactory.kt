package com.anstaendig.architecturecomponents.remote.service

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

private const val baseUrl = "https://data-candidate-api.zonartooling-test.zalan.do/v1/"

object SwapiServiceFactory {

  fun getService(isDebug: Boolean): SwapiService {
    return buildService(
        buildOkHttpClient(
            buildLoggingInterceptor(isDebug)
        ),
        buildMoshiConverterFactory()
    )
  }

  private fun buildService(
      okHttpClient: OkHttpClient,
      moshiConverterFactory: MoshiConverterFactory
  ): SwapiService {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(moshiConverterFactory)
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
        .create(SwapiService::class.java)
  }

  private fun buildOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder().apply {
      addInterceptor(httpLoggingInterceptor)
    }.build()
  }

  private fun buildMoshiConverterFactory(): MoshiConverterFactory {
    val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
    return MoshiConverterFactory.create(moshi)
  }

  private fun buildLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
    return HttpLoggingInterceptor()
        .setLevel(
            if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
  }
}
