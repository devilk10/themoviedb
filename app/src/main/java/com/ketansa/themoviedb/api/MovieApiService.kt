package com.ketansa.themoviedb.api

import com.ketansa.themoviedb.BuildConfig
import com.ketansa.themoviedb.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class MovieApiService {
    private val api: MovieApi

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit.create(MovieApi::class.java)
    }

    suspend fun getAllMovies(): DiscoverMovieResponse {
        return api.getAllMovies()
    }
}


interface MovieApi {
    @GET("discover/movie")
    suspend fun getAllMovies(@Query("api_key") apiKey: String = BuildConfig.ApiKey): DiscoverMovieResponse
}