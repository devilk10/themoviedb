package com.ketansa.themoviedb

import com.ketansa.themoviedb.repository.MovieRepository
import com.ketansa.themoviedb.ui.movieDetails.MovieDetailsViewModel
import com.ketansa.themoviedb.ui.movieList.MovieListViewModel

// Definition of a Factory interface with a function to create objects of a type
interface Factory<T> {
    fun create(): T
}

class MovieViewModelFactory(private val movieRepository: MovieRepository) :
    Factory<MovieListViewModel> {
    override fun create(): MovieListViewModel {
        return MovieListViewModel(movieRepository)
    }
}

class MovieDetailsViewModelFactory(private val movieRepository: MovieRepository) :
    Factory<MovieDetailsViewModel> {
    override fun create(): MovieDetailsViewModel {
        return MovieDetailsViewModel(movieRepository)
    }
}