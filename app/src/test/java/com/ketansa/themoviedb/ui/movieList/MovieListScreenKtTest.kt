package com.ketansa.themoviedb.ui.movieList

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.util.TestTag
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@Config(
    manifest = Config.NONE,
    sdk = [30]
)
@RunWith(RobolectricTestRunner::class)
class MovieListScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val mockViewModel: MovieListViewModel = mockk(relaxed = true)

    @Test
    fun movieCardShouldBePresentAnd() {
        val movie2 = Movie(
            2, "Wakanda Forever", "Overview 2", "posterPath2", Date(2323223232L),
        )
        val movie3 = Movie(3, "Movie 3", "Overview 3", "posterPath3", Date())
        val movie4 = Movie(4, "Movie 4", "Overview 4", "posterPath4", Date())
        val movie5 = Movie(5, "Movie 5", "Overview 5", "posterPath5", Date())
        val pagingData = PagingData.from(listOf(movie2, movie3, movie4, movie5))
        every { mockViewModel.loadAllMovies() } returns flow { pagingData }

        composeTestRule.setContent {
            MovieListScreen(mockViewModel)
        }
        composeTestRule.onNodeWithTag(TestTag.CARD).assertExists()
        composeTestRule.onNodeWithText("Wakanda Forever").assertExists()
        composeTestRule.onNodeWithText("28 Jan 1970").assertExists()
    }
}