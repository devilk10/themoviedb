package com.ketansa.themoviedb.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.domain.Movie

class MoviePagingSource(private val movieApiService: MovieApiService) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = movieApiService.getAllMovies(page = page)
            Log.d(
                "imptan",
                "loadAllMovies: movies ${response.results.size}}"
            )
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.results.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            Log.e(
                "imptan",
                "loadAllMovies: exception ${e.localizedMessage}}"
            )
            LoadResult.Error(e)
        }
    }
}