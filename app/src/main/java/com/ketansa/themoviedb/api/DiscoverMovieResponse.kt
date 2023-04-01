package com.ketansa.themoviedb.api

import com.ketansa.themoviedb.domain.Movie

data class DiscoverMovieResponse(
    val page: Int,
    val results: List<Movie>
)