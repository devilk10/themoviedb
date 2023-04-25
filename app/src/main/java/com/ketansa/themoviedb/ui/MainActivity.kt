package com.ketansa.themoviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ketansa.themoviedb.AppContainer
import com.ketansa.themoviedb.TMDBApp
import com.ketansa.themoviedb.ui.movieDetails.MovieDetailsScreen
import com.ketansa.themoviedb.ui.movieList.MovieListScreen
import com.ketansa.themoviedb.ui.theme.TheMovieDBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmdbApp = application as TMDBApp
        val appContainer = tmdbApp.appContainer
        setContent {
            TheMovieDBTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "moviesList"
                ) {
                    composable("moviesList") {
                        MovieListScreen(
                            appContainer.movieViewModel
                        ) { movieId ->
                            navController.navigate("movieDetails/$movieId")
                        }
                    }
                    composable(
                        "movieDetails/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId")
                        movieId?.let { MovieDetailsScreen(appContainer.movieDetailsViewModel, it) }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheMovieDBTheme {
        MovieListScreen(AppContainer().movieViewModel) {}
    }
}