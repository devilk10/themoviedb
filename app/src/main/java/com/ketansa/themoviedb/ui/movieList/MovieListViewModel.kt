package com.ketansa.themoviedb.ui.movieList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import com.ketansa.themoviedb.util.AppDispatchers
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val movieRepository: MovieRepository,
    private val appDispatchers: AppDispatchers
) : ViewModel() {

    private val _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies: State<List<Movie>> = _movies

    private val _error = mutableStateOf(false)
    val error: State<Boolean> = _error

    init {
        loadAllMovies()
    }

    private fun loadAllMovies() {
        viewModelScope.launch(appDispatchers.IO) {
            when (val response = movieRepository.getAllMovies()) {
                is Response.Success -> _movies.value = response.data
                is Response.Error -> _error.value = true
            }
        }
    }
}
