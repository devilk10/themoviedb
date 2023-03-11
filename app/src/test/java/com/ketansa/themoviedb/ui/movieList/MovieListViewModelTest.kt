package com.ketansa.themoviedb.ui.movieList

import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import com.ketansa.themoviedb.util.AppDispatchers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class MovieListViewModelTest {

    private lateinit var movieListViewModel: MovieListViewModel
    private val mockRepository = mockk<MovieRepository>()

    private val testDispatcher = AppDispatchers(UnconfinedTestDispatcher())

    @Test
    fun shouldSetMoviesWhenRepositoryReturnsSuccessResponse() = runBlocking {
        val allMovies = listOf(
            Movie("new movie", Date(), "url")
        )
        coEvery { mockRepository.getAllMovies() } returns Response.Success(allMovies)
        movieListViewModel = MovieListViewModel(mockRepository, testDispatcher)

        Assert.assertEquals(allMovies, movieListViewModel.movies.value)
    }

    @Test
    fun shouldSetErrorWhenErrorResponseIsReturnedFromRepository() = runBlocking {
        coEvery { mockRepository.getAllMovies() } returns Response.Error("")
        movieListViewModel = MovieListViewModel(mockRepository, testDispatcher)

        Assert.assertEquals(emptyList<Movie>(), movieListViewModel.movies.value)
        Assert.assertEquals(true, movieListViewModel.error.value)
    }
}