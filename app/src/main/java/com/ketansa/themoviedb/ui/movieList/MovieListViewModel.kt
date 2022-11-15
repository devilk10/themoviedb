package com.ketansa.themoviedb.ui.movieList

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import com.ketansa.themoviedb.util.AppDispatchers
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val movieRepository: MovieRepository,
    private val appDispatchers: AppDispatchers
) : ViewModel() {
    val movies = mutableStateOf<List<Movie>>(emptyList())

    init {
        loadAllMovies()
    }

    private fun loadAllMovies() {
        viewModelScope.launch(appDispatchers.IO) {
            movies.value = movieRepository.getAllMovies()
        }
    }
}
