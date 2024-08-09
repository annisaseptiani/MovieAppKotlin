package com.example.movieapp.ListAllMovie

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ListMovieScreen(type: String, navHostController: NavHostController,
                    listMovieViewModel: ListMovieViewModel) {
    LaunchedEffect(type) {

    }
    Text(text = "All Popular Movie", style = MaterialTheme.typography.titleLarge)
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(8.dp),modifier = Modifier.fillMaxSize()) {
                
    }
}

@Composable
fun ListItems() {

}