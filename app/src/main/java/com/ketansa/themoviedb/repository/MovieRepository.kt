package com.ketansa.themoviedb.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ketansa.themoviedb.api.MovieApiService

class MovieRepository(private val movieApiService: MovieApiService) {
    fun getAllMovies() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            MoviePagingSource(movieApiService)
        }
    ).flow
}
