package com.aunkhtoo.jetpackpaging.network

import com.aunkhtoo.jetpackpaging.BuildConfig
import com.aunkhtoo.jetpackpaging.network.auth.AuthInterceptor
import com.aunkhtoo.jetpackpaging.network.jsonadapter.LocalDateJsonAdapter
import com.aunkhtoo.jetpackpaging.network.jsonadapter.MovieIdJsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
Created By Aunt Htoo Aung on 08/11/2023.
 **/

object OkhttpProvider {

  private var okHttpClient: OkHttpClient? = null

  fun provide(): OkHttpClient {

    if (okHttpClient == null) {

      val okHttpClientBuilder = OkHttpClient.Builder()

      okHttpClientBuilder.addNetworkInterceptor(AuthInterceptor())

      if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
      }

      return okHttpClientBuilder
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
    }

    return okHttpClient!!

  }

}

object RetrofitProvider {

  private var retrofit: Retrofit? = null

  fun provide(): Retrofit {

    if (retrofit == null) {

      val moshi = Moshi.Builder()
        .add(MovieIdJsonAdapter())
        .add(LocalDateJsonAdapter())
        .build()

      retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(OkhttpProvider.provide())
        .build()
    }

    return retrofit!!
  }
}

object MovieServiceProvider {

  private var movieService: MovieService? = null

  fun createInstance(): MovieService {

    if (movieService == null) {
      movieService = RetrofitProvider.provide().create(MovieService::class.java)
    }

    return movieService!!
  }
}

