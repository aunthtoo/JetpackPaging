package com.aunkhtoo.jetpackpaging.network

import com.aunkhtoo.jetpackpaging.network.response.BaseMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
Created By Aunt Htoo Aung on 08/11/2023.
 **/
interface MovieService {

  @GET("/3/movie/popular")
  fun getPopularMovies(@Query("page") page: Int): Call<BaseMovieResponse>

}