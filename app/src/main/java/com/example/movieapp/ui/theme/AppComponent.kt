package com.example.movieapp.ui.theme

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R

@Composable
fun MovieCard(movieImage : String, movieTitle : String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(Modifier.size(150.dp), shape = RoundedCornerShape(10)) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
//            AsyncImage(model = movieImage, contentDescription = "Movie Image")
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = movieTitle, style = MaterialTheme.typography.titleMedium)
    }

}

@Composable
fun CustomLabel(labelText : String, labelColor : Color) {
    Card( shape = RoundedCornerShape(50), colors = CardDefaults.cardColors(containerColor = labelColor)) {
        Text(modifier = Modifier.padding(5.dp), text = labelText)
    }
}

@Composable
fun MovieList() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {

    }
}

@Preview
@Composable
fun ComponentPreview() {
    MovieCard(movieImage = "skksksks", movieTitle = "Minong")

}
