package com.aunkhtoo.jetpackpaging.feature.popularmovie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aunkhtoo.jetpackpaging.model.Movie
import com.aunkhtoo.jetpackpaging.network.MovieRemoteDataSource
import kotlinx.coroutines.delay
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 08/11/2023.
 **/
class PopularMoviePagingSource(private val movieRemoteDataSource: MovieRemoteDataSource) :
  PagingSource<Int, Movie>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
    return try {

      val pageIndex = params.key ?: 1

      val movies = movieRemoteDataSource.getPopularMovies(page = pageIndex)

      val prevKey = if (pageIndex == 1) null else pageIndex - 1

      val nextKey = if (movies.isEmpty()) null else pageIndex + 1

      return LoadResult.Page(
        data = movies,
        prevKey = prevKey,
        nextKey = nextKey
      )

    } catch (t: Throwable) {
      Timber.e(t)
      LoadResult.Error(t)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
    return state.anchorPosition
  }
}