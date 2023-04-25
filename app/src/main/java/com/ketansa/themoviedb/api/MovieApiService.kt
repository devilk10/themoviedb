package com.ketansa.themoviedb.api

import com.ketansa.themoviedb.BuildConfig
import com.ketansa.themoviedb.domain.Movie
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.IOException

class MovieApiService(retrofit: Retrofit) {

    private val api: MovieApi = retrofit.create(MovieApi::class.java)

    suspend fun getAllMovies(page: Int): DiscoverMovieResponse {
        return api.getAllMovies(page = page)
    }

    suspend fun getDetailsOf(movieId: Int): Movie {
        return api.getDetailsOf(movieId)
    }
}

interface MovieApi {
    @GET("discover/movie")
    suspend fun getAllMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.ApiKey
    ): DiscoverMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailsOf(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.ApiKey,
        @Query("language") language: String = "en-US"
    ): Movie
}