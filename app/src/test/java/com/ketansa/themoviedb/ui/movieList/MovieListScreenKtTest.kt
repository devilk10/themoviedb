package com.ketansa.themoviedb.ui.movieList

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.util.TestTag
import io.mockk.every
import io.mockk.mockk
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
    fun movieCardShouldBePresent() {
        every { mockViewModel.movies } returns mutableStateOf(
            listOf(
                Movie(
                    "Wakanda Forever",
                    Date(2323223232L),
                    "/some.jpg"
                )
            )
        )
        composeTestRule.setContent {
            MovieListScreen(mockViewModel)
        }
        composeTestRule.onNodeWithTag(TestTag.CARD).assertExists()
        composeTestRule.onNodeWithText("Wakanda Forever").assertExists()
        composeTestRule.onNodeWithText("28 Jan 1970").assertExists()
    }
}