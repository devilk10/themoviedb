package com.ketansa.themoviedb.api

import com.google.gson.annotations.SerializedName
import java.util.*

data class DiscoverMovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieResponse>,
)

data class MovieResponse(
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: Date,
    @SerializedName("poster_path") val imageUrl: String,
)