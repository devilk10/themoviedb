package com.ketansa.themoviedb.ui.movieList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import com.ketansa.themoviedb.util.AppDispatchers
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(
    private val movieRepository: MovieRepository,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    private val _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies: State<List<Movie>> = _movies

    private val _error = mutableStateOf(false)
    val error: State<Boolean> = _error

    fun loadAllMovies(): Flow<PagingData<Movie>> =
        movieRepository.getAllMovies().cachedIn(viewModelScope)
}
