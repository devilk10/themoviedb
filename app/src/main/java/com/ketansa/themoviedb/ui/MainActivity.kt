package com.ketansa.themoviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ketansa.themoviedb.AppContainer
import com.ketansa.themoviedb.TMDBApp
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieListScreen(appContainer.movieViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheMovieDBTheme {
        MovieListScreen(AppContainer().movieViewModel)
    }
}