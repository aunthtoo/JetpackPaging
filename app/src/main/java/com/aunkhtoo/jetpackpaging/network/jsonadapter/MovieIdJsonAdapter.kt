package com.aunkhtoo.jetpackpaging.network.jsonadapter

import com.aunkhtoo.jetpackpaging.model.MovieId
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

/**
Created By Aunt Htoo Aung on 08/11/2023.
 **/
class MovieIdJsonAdapter {
  @ToJson
  fun toJson(value: MovieId): Int {
    return value.value
  }

  @FromJson
  fun fromJson(value: Int): MovieId {
    return MovieId(value = value)
  }

}