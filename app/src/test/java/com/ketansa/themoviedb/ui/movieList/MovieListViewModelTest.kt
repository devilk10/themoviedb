package com.ketansa.themoviedb.ui.movieList

import com.ketansa.themoviedb.MainCoroutineRule
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import com.ketansa.themoviedb.util.AppDispatchers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class MovieListViewModelTest {

    private lateinit var movieListViewModel: MovieListViewModel
    private val mockRepository = mockk<MovieRepository>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private val testDispatcher = AppDispatchers(
        IO = TestCoroutineDispatcher()
    )

    @Test
    fun loadAllMovies() = runBlocking {
        val allMovies = listOf(
            Movie("new movie", Date(), "url")
        )
        coEvery { mockRepository.getAllMovies() } returns allMovies
        movieListViewModel = MovieListViewModel(mockRepository, testDispatcher)

        Assert.assertEquals(allMovies, movieListViewModel.movies.value)
    }
}