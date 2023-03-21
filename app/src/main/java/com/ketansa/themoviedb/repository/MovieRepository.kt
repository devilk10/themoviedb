package com.ketansa.themoviedb.repository

import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.util.Constants.ErrorCodes.INVALID_RESPONSE
import com.ketansa.themoviedb.util.Constants.ErrorCodes.NO_INTERNET
import java.net.SocketException

class MovieRepository(private val movieApiService: MovieApiService) {
    suspend fun getAllMovies(): Response<List<Movie>> {
        return try {
            Response.Success(
                movieApiService.getAllMovies().results.map {
                    Movie(it.title, it.releaseDate, it.imageUrl)
                }
            )
        } catch (e: SocketException) {
            Response.Error(NO_INTERNET, "Socket exception ${e.localizedMessage}")
        } catch (e: Exception) {
            Response.Error(INVALID_RESPONSE, "Invalid Response ${e.localizedMessage}")
        }
    }
}
