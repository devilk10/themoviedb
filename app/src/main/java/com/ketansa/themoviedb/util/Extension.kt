package com.ketansa.themoviedb.util

import android.annotation.SuppressLint
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.toNormalDate(): String {
    val formatter: Format = SimpleDateFormat("dd MMM yyyy")
    return formatter.format(this)
}