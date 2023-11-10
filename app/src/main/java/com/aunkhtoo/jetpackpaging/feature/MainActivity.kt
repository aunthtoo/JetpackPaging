package com.aunkhtoo.jetpackpaging.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aunkhtoo.jetpackpaging.feature.popularmovie.PopularMovieScreen

/**
Created By Aunt Htoo Aung on 09/11/2023.
 **/
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PopularMovieScreen(viewModel = viewModel())
    }
  }
}