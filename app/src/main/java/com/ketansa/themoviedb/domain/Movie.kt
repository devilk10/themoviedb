package com.ketansa.themoviedb.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: Date,
)
