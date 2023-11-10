package com.aunkhtoo.jetpackpaging.network.auth

import com.aunkhtoo.jetpackpaging.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
Created By Aunt Htoo Aung on 08/11/2023.
 **/
class AuthInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val newUrl =
      chain.request().url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
    return chain.proceed(chain.request().newBuilder().url(newUrl).build())
  }
}