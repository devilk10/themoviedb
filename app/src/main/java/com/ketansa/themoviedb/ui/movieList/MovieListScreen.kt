package com.ketansa.themoviedb.ui.movieList

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
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
            itemsIndexed(movies) { index, item ->
                if (item != null) {
                    MovieCard(item = item)
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
        }
//        Column {
//            when (val state = movies.loadState.refresh) { //FIRST LOAD
//                is LoadState.Error -> {
//                    //TODO Error Item
//                    Text(text = "Pagination error")
//                    //state.error to get error message
//                }
//                is LoadState.Loading -> { // Loading UI
//                    Column(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                    ) {
//                        Text(
//                            modifier = Modifier
//                                .padding(8.dp),
//                            text = "Refresh Loading"
//                        )
//                        CircularProgressIndicator(color = Color.Black)
//                    }
//                }
//                else -> {}
//            }
//            when (val state = movies.loadState.append) { // Pagination
//                is LoadState.Error -> {
//                    //TODO Pagination Error Item
//                    Text(text = "Pagination error")
//                    //state.error to get error message
//                }
//                is LoadState.Loading -> { // Pagination Loading UI
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                    ) {
//                        Text(text = "Pagination Loading")
//
//                        CircularProgressIndicator(color = Color.Black)
//                    }
//                }
//                else -> {}
//            }
//        }

    }
}

@Composable
fun MovieCard(item: Movie) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
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
                Text(text = item.releaseDate?.toNormalDate() ?: "01/01/1970")
            }
        }
    }
}