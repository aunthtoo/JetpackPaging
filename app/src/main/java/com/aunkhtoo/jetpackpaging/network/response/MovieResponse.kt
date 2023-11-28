package com.aunkhtoo.jetpackpaging.network.response


import com.aunkhtoo.jetpackpaging.model.Movie
import com.aunkhtoo.jetpackpaging.model.MovieId
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class MovieResponse(
  @Json(name = "id")
  val id: MovieId,
  @Json(name = "adult")
  val adult: Boolean,
  @Json(name = "backdrop_path")
  val backdropPath: String?,
  @Json(name = "genre_ids")
  val genreIds: List<Int>,
  @Json(name = "original_language")
  val originalLanguage: String,
  @Json(name = "original_title")
  val originalTitle: String,
  @Json(name = "overview")
  val overview: String,
  @Json(name = "popularity")
  val popularity: Double,
  @Json(name = "poster_path")
  val posterPath: String,
  @Json(name = "release_date")
  val releaseDate: LocalDate,
  @Json(name = "title")
  val title: String,
  @Json(name = "video")
  val video: Boolean,
  @Json(name = "vote_average")
  val voteAverage: Double,
  @Json(name = "vote_count")
  val voteCount: Int
) {

  fun mapToMovie() = Movie(
    id = id,
    adult = adult,
    backdropPath = if (backdropPath == null) "https://c4.wallpaperflare.com/wallpaper/799/191/755/godzilla-movies-digital-art-movie-poster-wallpaper-preview.jpg" else "https://image.tmdb.org/t/p/original$backdropPath",
    genreIds = genreIds,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = "https://image.tmdb.org/t/p/original$posterPath",
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
  )
}