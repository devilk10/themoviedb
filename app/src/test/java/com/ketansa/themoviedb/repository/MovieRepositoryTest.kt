import com.ketansa.themoviedb.api.MovieApiService
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class MovieRepositoryTest {

    private lateinit var movieRepository: MovieRepository
    private val movieApiService = mockk<MovieApiService>()

    @Before
    fun setup() {
        movieRepository = MovieRepository(movieApiService)
    }

    @Test(expected = Exception::class)
    fun `test getAllMovies throws exception`() = runBlocking {
        // Arrange
        val exception = Exception("Test Exception")
        coEvery { movieApiService.getAllMovies(any()) } throws exception

        // Act
        coEvery { movieRepository.getAllMovies() } throws exception
        movieRepository.getAllMovies().collect()
    }

    @Test
    fun shouldFetchMovieDetailsOf() = runBlocking {
        val movie = Movie(
            id = 7562,
            title = "ridiculus",
            overview = "facilisis",
            posterPath = "nihil",
            releaseDate = Date()
        )
        coEvery { movieApiService.getDetailsOf(1234) } returns movie
        assertEquals(movieRepository.getDetailsOf(1234), movie)
    }
}