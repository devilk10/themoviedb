package com.ketansa.themoviedb.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.domain.Movie
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
}
