package com.ketansa.themoviedb.repository

import com.ketansa.themoviedb.api.DiscoverMovieResponse
import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.api.MovieResponse
import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.util.Constants.ErrorCodes.INVALID_RESPONSE
import com.ketansa.themoviedb.util.Constants.ErrorCodes.NO_INTERNET
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.SocketException
import java.util.*

@RunWith(JUnit4::class)
class MovieRepositoryTest {

    private val mockApiService: MovieApiService = mockk()

    @Test
    fun getAllMovies() = runBlocking {
        val movieResponse = MovieResponse("Hello", Date(), "url")
        coEvery { mockApiService.getAllMovies(page) } returns DiscoverMovieResponse(
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
            Response.Error(INVALID_RESPONSE, "Invalid Response "),
            MovieRepository(mockApiService).getAllMovies()
        )
    }

    @Test
    fun shouldReturnErrorWhenApiThrowsSocketException() = runBlocking {
        coEvery { mockApiService.getAllMovies() } throws SocketException("no internet")

        Assert.assertEquals(
            Response.Error(NO_INTERNET, "Socket exception no internet"),
            MovieRepository(mockApiService).getAllMovies()
        )
    }
}