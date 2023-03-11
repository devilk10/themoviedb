package com.ketansa.themoviedb.repository

import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.domain.Movie

class MovieRepository(private val movieApiService: MovieApiService) {
    suspend fun getAllMovies(): Response<List<Movie>> {
        return try {
            Response.Success(
                movieApiService.getAllMovies().results.map {
                    Movie(it.title, it.releaseDate, it.imageUrl)
                }
            )
        } catch (e: Exception) {
            Response.Error("Hi, unable to parse response ${e.localizedMessage}")
        }
    }
}
