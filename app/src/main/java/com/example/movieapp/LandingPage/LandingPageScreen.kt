package com.example.movieapp.LandingPage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun MovieList() {
    PopularMovieList()
    FavoritMovieList()
}

@Composable
fun PopularMovieList() {
    Column {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Popular Movies", style = MaterialTheme.typography.titleLarge)
            Text(text = "See All", style = MaterialTheme.typography.bodySmall, textDecoration = TextDecoration.Underline)
        }

    }
}

@Composable
fun FavoritMovieList() {
    Column {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Favorite Movies", style = MaterialTheme.typography.titleLarge)
            Text(text = "See All", style = MaterialTheme.typography.bodySmall, textDecoration = TextDecoration.Underline)
        }
    }
}