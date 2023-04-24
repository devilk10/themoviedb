package com.ketansa.themoviedb.ui.movieList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun loadAllMovies(): Flow<PagingData<Movie>> =
        movieRepository.getAllMovies().cachedIn(viewModelScope)
}
