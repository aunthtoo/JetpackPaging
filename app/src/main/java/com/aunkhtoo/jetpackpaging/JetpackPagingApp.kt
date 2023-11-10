package com.aunkhtoo.jetpackpaging

import android.app.Application
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 07/11/2023.
 **/
class JetpackPagingApp : Application() {

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

  }
}