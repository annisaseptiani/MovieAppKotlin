package com.example.movieapp.ui.theme

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.domain.model.PopularMovie

@Composable
fun MovieCard(title: String, image: String) {

            Column(Modifier.padding(10.dp),verticalArrangement = Arrangement.Center) {
                AsyncImage(
                    modifier = Modifier.width(200.dp),
                    model = image,
                    contentDescription = "Movie Image"
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    minLines = 2
                )
            }

}
@Composable
fun CustomLabel(labelText : String, labelColor : Color) {
    Card( shape = RoundedCornerShape(50), colors = CardDefaults.cardColors(containerColor = labelColor)) {
        Text(modifier = Modifier.padding(5.dp), text = labelText)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ComponentPreview() {

}
