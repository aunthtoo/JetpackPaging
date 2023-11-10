package com.aunkhtoo.jetpackpaging.network.jsonadapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
Created By Aunt Htoo Aung on 08/11/2023.
 **/
class LocalDateJsonAdapter {

  companion object {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  }

  @ToJson
  fun toJson(value: LocalDate): String {
    return value.format(formatter)
  }

  @FromJson
  fun fromJson(value: String): LocalDate {
    return LocalDate.parse(value, formatter)
  }
}