package com.aunkhtoo.jetpackpaging.network

import com.aunkhtoo.jetpackpaging.model.Movie
import com.aunkhtoo.jetpackpaging.network.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 08/11/2023.
 **/
class MovieRemoteDataSource {

  private val movieService: MovieService by lazy {
    MovieServiceProvider.createInstance()
  }

  suspend fun getPopularMovies(page: Int): List<Movie> {

    return withContext(Dispatchers.IO) {
      val response = movieService.getPopularMovies(page = page).execute().body()!!

      response.results.map(MovieResponse::mapToMovie)
    }
  }
}