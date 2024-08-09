package com.example.movieapp.ListAllMovie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.domain.model.PopularMovie
import com.example.movieapp.ui.theme.MainColor
import com.example.movieapp.ui.theme.MovieCardLong
import com.example.movieapp.ui.theme.Routes

@Composable
fun ListMovieScreen(type: String, navHostController: NavHostController,
                    viewModel: ListMovieViewModel) {
    var movieTypes by remember {
        mutableStateOf<String?>("")
    }
    LaunchedEffect(type) {
        type.let {
            movieTypes = type
        }
    }

    if (movieTypes.equals("popular")) {
        Column {
            Text(modifier = Modifier.padding(20.dp).fillMaxWidth(),
                text = "All Populars Movie",
                style = MaterialTheme.typography.titleLarge,
                color = MainColor, textAlign = TextAlign.Center)
            LoadPopularMovies(viewModel = viewModel, navHostController)
        }
    } else {
        Column {
            Text(modifier = Modifier.padding(20.dp).fillMaxWidth(),
                text = "All Favorites Movie",
                style = MaterialTheme.typography.titleLarge,
                color = MainColor, textAlign = TextAlign.Center)
            LoadFavoritesMovies(viewModel = viewModel, navHostController)
        }
    }

}

@Composable
fun LoadFavoritesMovies(viewModel: ListMovieViewModel, navHostController: NavHostController) {
    val favoriteMovies by viewModel.favoriteMovies.collectAsState()
    if (favoriteMovies.isEmpty()) {
        Text(text = "No Favorite movie. \nClick on movie poster to add movie to favorite",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Gray, modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center)
    } else {
        FavoriteMovieList(list = favoriteMovies, navHostController = navHostController)
    }
}

@Composable
fun FavoriteMovieList(list: List<PopularMovie>, navHostController: NavHostController) {
    LazyColumn {
        items(list.size) {
                index ->
            val str = list[index]
            MovieCardLong(title = str.title, release = str.release_date,
                poster = str.poster_path, rating = str.vote_average.toString()) {
                navHostController.navigate("${Routes.Detail.routes}/${str.id}")
            }
        }
    }
}

@Composable
fun LoadPopularMovies(viewModel: ListMovieViewModel, navHostController: NavHostController) {
    val lazyItems : LazyPagingItems<PopularMovie> = viewModel.paging.collectAsLazyPagingItems()

    ListPopularItems(lazyItems, navHostController = navHostController)
}

@Composable
fun ListPopularItems(movies: LazyPagingItems<PopularMovie>, navHostController: NavHostController) {
    val loadState = movies.loadState

    LazyColumn {
        items(movies.itemCount) {
            index ->
            val str = movies[index]
            str?.let {
                MovieCardLong(title = str.title, release = str.release_date,
                    poster = str.poster_path, rating = str.vote_average.toString()) {
                    navHostController.navigate("${Routes.Detail.routes}/${str.id}")
                }
            }
        }

        when {
            loadState.refresh is LoadState.Loading -> {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            loadState.append is LoadState.Loading -> {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            loadState.refresh is LoadState.Error -> {
                val e = movies.loadState.refresh as LoadState.Error
                item {
                    BasicText(
                        text = "Refresh error: ${e.error.localizedMessage}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            loadState.append is LoadState.Error -> {
                val e = movies.loadState.append as LoadState.Error
                item {
                    BasicText(
                        text = "Append error: ${e.error.localizedMessage}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}