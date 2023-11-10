package com.aunkhtoo.jetpackpaging.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
Created By Aunt Htoo Aung on 10/11/2023.
 **/

fun LocalDate.format(formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")): String {
  return this.format(formatter)
}

fun Context.showShortToast(textMessage: String) {
  Toast.makeText(this, textMessage, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(textMessage: String) {
  Toast.makeText(this, textMessage, Toast.LENGTH_LONG).show()
}

fun Context.showShortToast(@StringRes textMessageId: Int) {
  Toast.makeText(this, textMessageId, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(@StringRes textMessageId: Int) {
  Toast.makeText(this, textMessageId, Toast.LENGTH_LONG).show()
}