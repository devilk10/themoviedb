package com.ketansa.themoviedb.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ketansa.themoviedb.api.DiscoverMovieResponse
import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.domain.Movie
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

class MoviePagingSourceTest {

    private lateinit var moviePagingSource: MoviePagingSource

    private val movieApiService = mockk<MovieApiService>()

    @Before
    fun setup() {
        moviePagingSource = MoviePagingSource(movieApiService)
    }

    @Test
    fun `test load success`() = runBlocking {
        // Arrange
        val page = 1
        val movieList = listOf(
            Movie(
                id = 1, title = "Test Movie 1",
                overview = "ante",
                posterPath = "perpetua",
                releaseDate = Date(),
            ), Movie(
                id = 2, title = "Test Movie 2",
                overview = "deterruisset",
                posterPath = "possit",
                releaseDate = Date()
            )
        )
        val response = DiscoverMovieResponse(page = page, results = movieList)
        coEvery { movieApiService.getAllMovies(page) } returns response

        // Act
        val loadParams = PagingSource.LoadParams.Refresh<Int>(
            null,
            loadSize = 4,
            placeholdersEnabled = false,
        )
        val result = moviePagingSource.load(loadParams)

        // Assert
        val expected = PagingSource.LoadResult.Page(
            data = movieList,
            prevKey = null,
            nextKey = 2
        )
        assertEquals(result, expected)
    }

    @Test
    fun `test load error`() = runBlocking {
        // Arrange
        val exception = Exception("Test Exception")
        coEvery { movieApiService.getAllMovies(any()) } throws exception

        // Act
        val loadParams = PagingSource.LoadParams.Refresh<Int>(null, 10, false)
        val result = moviePagingSource.load(loadParams)

        // Assert
        assertTrue(result is PagingSource.LoadResult.Error)
        assertEquals((result as PagingSource.LoadResult.Error).throwable, exception)
    }

    @Test
    fun `test getRefreshKey returns correct value`() {
        // Arrange
        val pagingState = mockk<PagingState<Int, Movie>>()
        val anchorPosition = 20
        val closestPage = mockk<PagingSource.LoadResult.Page<Int, Movie>>()
        every { closestPage.prevKey } returns 2
        every { pagingState.anchorPosition } returns anchorPosition
        every { pagingState.closestPageToPosition(anchorPosition) } returns closestPage

        // Act
        val result = moviePagingSource.getRefreshKey(pagingState)

        // Assert
        assertEquals(result, 3)
    }
}
