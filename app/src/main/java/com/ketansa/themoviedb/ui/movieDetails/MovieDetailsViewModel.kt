package com.ketansa.themoviedb.ui.movieDetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val detailsState = mutableStateOf<Response<Movie>>(Response.Loading)

    fun fetchDetails(movieId: Int) {
        viewModelScope.launch {
            when (val details = movieRepository.getDetailsOf(movieId)) {
                is Response.Success -> detailsState.value = details
                is Response.Error -> detailsState.value = details
                else -> {}
            }
        }

    }
}