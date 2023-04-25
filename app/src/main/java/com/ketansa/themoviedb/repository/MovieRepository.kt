package com.ketansa.themoviedb.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.util.Constants
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieApiService: MovieApiService) {
    fun getAllMovies(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            MoviePagingSource(movieApiService)
        }
    ).flow

    suspend fun getDetailsOf(movieId: Int): Response<Movie> {
        return try {
            val movie = movieApiService.getDetailsOf(movieId)
            Response.Success(movie)
        } catch (e: Exception) {
            Response.Error(Constants.ErrorCodes.INVALID_RESPONSE, e.localizedMessage ?: "")
        }
    }

}
