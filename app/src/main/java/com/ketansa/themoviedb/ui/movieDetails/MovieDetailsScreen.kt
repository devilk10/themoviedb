package com.ketansa.themoviedb.ui.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ketansa.themoviedb.api.Response
import com.ketansa.themoviedb.util.Constants

@Composable
fun MovieDetailsScreen(movieDetailsVM: MovieDetailsViewModel, movieId: Int) {
    movieDetailsVM.fetchDetails(movieId)
    val detailsState = remember { movieDetailsVM.detailsState }

    val bannerHeight =
        with(LocalDensity.current) { (LocalConfiguration.current.screenHeightDp * 0.4).dp }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val response = detailsState.value) {
            is Response.Error -> Text(
                text = response.message, modifier = Modifier.align(Alignment.Center)
            )

            Response.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            is Response.Success -> {
                val movie = response.data
                Column(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = rememberImagePainter(Constants.POSTER_BASE_URL + movie.posterPath),
                        contentDescription = "movie_poster",
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(bannerHeight)
                    )
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = movie.title, style = MaterialTheme.typography.h4)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = movie.overview, style = MaterialTheme.typography.body1)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Release Date: ${movie.releaseDate}",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }

            }
        }
    }
}