package com.aunkhtoo.jetpackpaging.network.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseMovieResponse(
  @Json(name = "page")
  val page: Int,
  @Json(name = "results")
  val results: List<MovieResponse>,
  @Json(name = "total_pages")
  val totalPages: Int,
  @Json(name = "total_results")
  val totalResults: Int
)