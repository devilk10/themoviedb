package com.ketansa.themoviedb

import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.repository.MovieRepository
import com.ketansa.themoviedb.ui.movieDetails.MovieDetailsViewModel
import com.ketansa.themoviedb.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {
    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    private val movieRepository = MovieRepository(MovieApiService(retrofit))
    val movieViewModel = MovieViewModelFactory(movieRepository).create()
    val movieDetailsViewModel = MovieDetailsViewModelFactory(movieRepository).create()
}