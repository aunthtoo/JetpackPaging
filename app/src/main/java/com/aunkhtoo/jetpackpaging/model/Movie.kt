package com.aunkhtoo.jetpackpaging.model

import java.time.LocalDate

/**
Created By Aunt Htoo Aung on 08/11/2023.
 **/
data class Movie(
  val id: MovieId,
  val adult: Boolean,
  val backdropPath: String,
  val genreIds: List<Int>,
  val originalLanguage: String,
  val originalTitle: String,
  val overview: String,
  val popularity: Double,
  val posterPath: String,
  val releaseDate: LocalDate,
  val title: String,
  val video: Boolean,
  val voteAverage: Double,
  val voteCount: Int
)

data class MovieId(val value: Int)
