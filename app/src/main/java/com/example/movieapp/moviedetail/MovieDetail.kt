package com.example.movieapp.moviedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp.ui.theme.CustomLabel
import com.example.movieapp.ui.theme.MainColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetail(id: Int, viewModel: MovieDetailViewModel, navHostController: NavHostController) {

    val isSaved by viewModel.saveState.collectAsState()

    var snackBarMessage by remember {
        mutableStateOf<String?>(null)
    }

    val detailMovie by viewModel.detailMovie.collectAsState()

    val scaffoldState = rememberBottomSheetScaffoldState()
    LaunchedEffect(id) {
        viewModel.getDetailData(id)
        viewModel.checkMovieSaved(id)
    }

    LaunchedEffect(snackBarMessage) {
        snackBarMessage?.let {
            scaffoldState.snackbarHostState.showSnackbar(it)
            snackBarMessage = null
        }
    }
    
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = scaffoldState.snackbarHostState)},
        content= {
            paddingvalues ->
            detailMovie?.let {
                Column(modifier = Modifier.padding(paddingvalues)) {
                    AsyncImage(modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(),
                        contentScale = ContentScale.FillWidth,
                        model = detailMovie?.poster_path, contentDescription = "movie image")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(), text = detailMovie!!.title,
                        style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold, color = MainColor)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp), horizontalArrangement = Arrangement.Center) {
                        for (genre in detailMovie!!.genres) {
                            CustomLabel(labelText = genre.name, labelColor = Color.Gray)
                            Spacer(modifier = Modifier.padding(5.dp))
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp), horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        CustomLabel(labelText = "IMDB ${detailMovie?.vote_average.toString()}", labelColor = MainColor)
                        if (isSaved) {
                            Row(horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { viewModel.deleteFromFavorite(id)
                                    snackBarMessage = "Removed from Favorites"}) {
                                Icon(tint = MainColor, imageVector = Icons.Outlined.Star,
                                    contentDescription = "icon favorite")
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(modifier = Modifier.padding(10.dp), text = "Remove From Favorite", style = MaterialTheme.typography.bodySmall,
                                    color = MainColor)
                            }
                        } else {
                            Row(horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { viewModel.addToFavorite(detailMovie!!)
                                    snackBarMessage = "Add to Favorites"}) {
                                Icon(imageVector = Icons.Outlined.Star,
                                    contentDescription = "icon favorite")
                                Spacer(modifier = Modifier.padding(5.dp))
                                Text(modifier = Modifier.padding(10.dp), text = "Add To Favorite", style = MaterialTheme.typography.bodySmall,
                                    color = MainColor)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(text = detailMovie!!.overview, modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                        textAlign = TextAlign.Justify)

                }
            }
        }
    )

}

@Preview
@Composable
fun MovieDetailPreview() {

}