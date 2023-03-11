package com.ketansa.themoviedb.ui.movieList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ketansa.themoviedb.R
import com.ketansa.themoviedb.ui.theme.Typography
import com.ketansa.themoviedb.util.Constants
import com.ketansa.themoviedb.util.TestTag
import com.ketansa.themoviedb.util.toNormalDate

@Composable
fun MovieListScreen(mealViewModel: MovieListViewModel) {
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
        LazyColumn {
            items(mealViewModel.movies.value) { item ->
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
                            painter = rememberImagePainter(Constants.IMAGE_BASE_URL + item.imageUrl),
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
                            Text(text = item.releaseDate.toNormalDate())
                        }
                    }
                }
            }
        }
    }
}
