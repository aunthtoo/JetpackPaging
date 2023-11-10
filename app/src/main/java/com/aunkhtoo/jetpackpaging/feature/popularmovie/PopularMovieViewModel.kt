package com.aunkhtoo.jetpackpaging.feature.popularmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aunkhtoo.jetpackpaging.model.Movie
import com.aunkhtoo.jetpackpaging.network.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 09/11/2023.
 **/
class PopularMovieViewModel : ViewModel() {

  fun loadPopularViewModel(): Flow<PagingData<Movie>> {
    val moviePagingSource =
      PopularMoviePagingSource(movieRemoteDataSource = MovieRemoteDataSource())

    return Pager(
      PagingConfig(pageSize = 20)
    ) {
      moviePagingSource
    }.flow.cachedIn(viewModelScope)

  }
}