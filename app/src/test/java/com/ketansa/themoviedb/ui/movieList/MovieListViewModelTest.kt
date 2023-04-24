package com.ketansa.themoviedb.ui.movieList

import androidx.paging.PagingData
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class MovieListViewModelTest {

    private lateinit var movieListViewModel: MovieListViewModel
    private val mockRepository = mockk<MovieRepository>()

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    //    @Ignore("Paging test dependency is not working as expected")
    @Test
    fun shouldSetMoviesWhenRepositoryReturnsSuccessResponse() = runTest {
        val movieData = listOf(
            Movie(
                id = 2254,
                title = "harum",
                overview = "dicta",
                posterPath = "signiferumque",
                releaseDate = Date()
            ), Movie(
                id = 2255,
                title = "karum",
                overview = "dicta",
                posterPath = "signiferumque",
                releaseDate = Date()
            )
        )
        val pagingData = PagingData.from(
            movieData
        )

        coEvery { mockRepository.getAllMovies() } returns flowOf(pagingData)
        movieListViewModel = MovieListViewModel(mockRepository)
        val result: PagingData<Movie> = movieListViewModel.loadAllMovies().take(1).single()
    }
}