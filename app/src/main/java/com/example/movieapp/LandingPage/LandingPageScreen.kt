package com.example.movieapp.LandingPage

import android.provider.Contacts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.domain.model.PopularMovie
import com.example.movieapp.ui.theme.MainColor
import com.example.movieapp.ui.theme.MovieCard
import com.example.movieapp.ui.theme.Routes
import io.reactivex.rxjava3.disposables.CompositeDisposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPageScreen(navHostController: NavHostController, viewModel: LandingPageViewModel) {

    val lazyItems : LazyPagingItems<PopularMovie> = viewModel.paging.collectAsLazyPagingItems()

    val favoriteMovies by viewModel.favoriteMovies.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(
                text = "Movie App",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            ) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MainColor))
        },
        content = {
            Column(modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())) {
                PopularMovieList(navHostController,lazyItems)
                FavoritMovieList(favoriteMovies, navHostController)
            }
        }
    )

}

@Composable
fun PopularMovieList(navHostController: NavHostController, movies: LazyPagingItems<PopularMovie>) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Top 10 Popular Movies",
                style = MaterialTheme.typography.titleMedium,
                color = MainColor
            )
            Row(modifier = Modifier.clickable {
                navHostController.navigate("${Routes.ListAll.routes}/popular")},
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "See All",
                    style = MaterialTheme.typography.bodySmall,
                    color = MainColor
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "next", tint = MainColor
                )
            }
        }
        PopularMovieListItem(navHostController,movies)
    }
}

@Composable
fun PopularMovieListItem(navHostController: NavHostController, movies: LazyPagingItems<PopularMovie>) {
    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {

        val limitedMovies = movies.itemSnapshotList.items.take(10)
        items(limitedMovies.size) { index ->
            val str =movies[index]
            MovieCard(title = str!!.title, image = str.poster_path, onClickItem = {
                navHostController.navigate("${Routes.Detail.routes}/${str.id}")
            })
        }
    }
}

@Composable
fun FavoriteMovieListItem(list: List<PopularMovie>, navHostController: NavHostController) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {

        items(list.size) { index ->
            val str =list[index]
            MovieCard(title = str.title, image = str.poster_path, onClickItem = {
                navHostController.navigate("${Routes.Detail.routes}/${str.id}")
            })
        }
    }
}

@Composable
fun FavoritMovieList(movies : List<PopularMovie>, navHostController: NavHostController) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Top 10 Favorite Movies", style = MaterialTheme.typography.titleMedium,
                color = MainColor)
            Row(modifier = Modifier.clickable {
                navHostController.navigate("${Routes.ListAll.routes}/favorites")},
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "See All", style = MaterialTheme.typography.bodySmall, color = MainColor)
                Icon(imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "next", tint = MainColor)
            }
        }
        if (movies.isEmpty()) {
            Text(text = "No Favorite movie. \nClick on movie poster to add movie to favorite",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray, modifier = Modifier.fillMaxWidth().padding(10.dp),
                textAlign = TextAlign.Center)
        } else {
            FavoriteMovieListItem(list = movies, navHostController = navHostController)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LandingPageScreenPreview() {

}