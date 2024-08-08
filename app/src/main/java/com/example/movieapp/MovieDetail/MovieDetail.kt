package com.example.movieapp.MovieDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.CustomLabel

@Composable
fun MovieDetail() {
    Column(Modifier.padding(10.dp)) {
        Image(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "movie image")
        CustomLabel(labelText = "IMDB 7.0", labelColor = Color.Yellow)
        Text(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp), text = "The House", style = MaterialTheme.typography.titleLarge)
        Row {
           CustomLabel(labelText = "Horror", labelColor = Color.Gray)
            Spacer(modifier = Modifier.padding(5.dp))
           CustomLabel(labelText = "Mistery", labelColor = Color.Red)
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
            Image(imageVector = Icons.Outlined.Star, contentDescription = "icon favorite")
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Favorite", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun MovieDetailPreview() {
    MovieDetail()
}