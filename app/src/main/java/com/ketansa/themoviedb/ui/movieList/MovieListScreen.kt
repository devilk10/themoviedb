package com.ketansa.themoviedb.ui.movieList

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.ketansa.themoviedb.R
import com.ketansa.themoviedb.domain.Movie
import com.ketansa.themoviedb.ui.theme.Typography
import com.ketansa.themoviedb.util.Constants
import com.ketansa.themoviedb.util.TestTag
import com.ketansa.themoviedb.util.toNormalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieListScreen(movieListViewModel: MovieListViewModel) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "TheMovieDB") },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                    contentDescription = "Movie Icon"
                )
            }
        )
    }) {
        val movies = movieListViewModel.loadAllMovies().collectAsLazyPagingItems()
        LazyColumn {
            items(movies) { movie ->
                movie?.let {
                    MovieCard(it)
                }
            }
            when (movies.loadState.append) {
                is LoadState.Error -> item { ErrorItem() }
                LoadState.Loading -> item { LoaderItem() }
                is LoadState.NotLoading -> Unit
            }

            when (movies.loadState.refresh) {
                is LoadState.Error -> item { ErrorItem() }
                LoadState.Loading -> item { LoaderItem() }
                is LoadState.NotLoading -> Unit
            }
        }
    }
}

@Composable
private fun LoaderItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(42.dp)
                .width(42.dp)
                .padding(8.dp), strokeWidth = 5.dp
        )
    }
}

@Composable
private fun ErrorItem() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier
                    .size(35.dp)
                    .padding(4.dp)
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Very dangerous error",
                style = MaterialTheme.typography.body2,
                color = Color.Red
            )
        }
    }
}

@Composable
fun MovieCard(item: Movie) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
            .testTag(TestTag.CARD)
    ) {
        Row {
            Image(
                painter = rememberImagePainter(Constants.IMAGE_BASE_URL + item.posterPath),
                contentDescription = "movie_poster",
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            ) {
                Text(text = item.title, style = Typography.h6)
                Text(text = item.releaseDate.toNormalDate() ?: "01/01/1970")
            }
        }
    }
}