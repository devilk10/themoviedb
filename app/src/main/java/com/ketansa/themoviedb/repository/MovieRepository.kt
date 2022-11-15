package com.ketansa.themoviedb.repository

import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.domain.Movie

class MovieRepository(private val movieApiService: MovieApiService = MovieApiService()) {
    suspend fun getAllMovies(): List<Movie> {
        return movieApiService.getAllMovies().results.map {
            Movie(it.title, it.releaseDate, it.imageUrl)
        }
    }
}
