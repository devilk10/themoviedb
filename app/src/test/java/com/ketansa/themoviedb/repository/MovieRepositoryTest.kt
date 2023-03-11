package com.ketansa.themoviedb.repository

import com.ketansa.themoviedb.api.DiscoverMovieResponse
import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.api.MovieResponse
import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.domain.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class MovieRepositoryTest {

    private val mockApiService: MovieApiService = mockk()

    @Test
    fun getAllMovies() = runBlocking {
        val movieResponse = MovieResponse("Hello", Date(), "url")
        coEvery { mockApiService.getAllMovies() } returns DiscoverMovieResponse(
            0, listOf(
                movieResponse
            )
        )
        Assert.assertEquals(
            MovieRepository(mockApiService).getAllMovies(),
            Response.Success(listOf(Movie("Hello", movieResponse.releaseDate, "url")))
        )
    }

    @Test
    fun shouldReturnErrorWhenApiThrowsAnException() = runBlocking {
        coEvery { mockApiService.getAllMovies() } throws Exception("")

        Assert.assertEquals(
            Response.Error("Hi, unable to parse response "),
            MovieRepository(mockApiService).getAllMovies()
        )
    }
}