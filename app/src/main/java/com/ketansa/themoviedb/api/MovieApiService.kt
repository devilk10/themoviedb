package com.ketansa.themoviedb.api

import com.ketansa.themoviedb.BuildConfig
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class MovieApiService(retrofit: Retrofit) {

    private val api: MovieApi = retrofit.create(MovieApi::class.java)

    suspend fun getAllMovies(): DiscoverMovieResponse {
        return api.getAllMovies()
    }
}

interface MovieApi {
    @GET("discover/movie")
    suspend fun getAllMovies(@Query("api_key") apiKey: String = BuildConfig.ApiKey): DiscoverMovieResponse
}