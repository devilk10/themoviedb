package com.ketansa.themoviedb.api

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import java.util.*

@RunWith(JUnit4::class)
class MovieApiServiceTest {

    private val mockRetrofit: Retrofit = mockk(relaxed = true)
    private val mockApi: MovieApi = mockk(relaxed = true)

    private lateinit var movieApiService: MovieApiService

    @Before
    fun setup() {
        every { mockRetrofit.create(MovieApi::class.java) } returns mockApi
        movieApiService = MovieApiService(mockRetrofit)
    }

    @Test
    fun getAllMoviesShouldReturnMovieResponse() = runBlocking {
        val discoverMovieResponse =
            DiscoverMovieResponse(0, listOf(MovieResponse("ping pong", Date(1234324L), "")))
        coEvery { mockApi.getAllMovies() } returns discoverMovieResponse

        assertEquals(
            movieApiService.getAllMovies(),
            discoverMovieResponse
        )
    }
}