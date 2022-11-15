package com.ketansa.themoviedb

import com.ketansa.themoviedb.repository.MovieRepository

class AppContainer {
    private val movieRepository = MovieRepository()
    val movieViewModel = MovieViewModelFactory(movieRepository).create()
}