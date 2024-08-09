package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.landingpage.LandingPageViewModel
import com.example.movieapp.listallmovie.ListMovieViewModel
import com.example.movieapp.moviedetail.MovieDetailViewModel
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val landingPageViewModel : LandingPageViewModel by viewModels()
    private val detailViewModel : MovieDetailViewModel by viewModels()
    private val listMovieViewModel : ListMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                val controller : NavHostController = rememberNavController()
                Scaffold() {
                    paddingValues ->
                    NavigationGraph(modifier = Modifier.padding(paddingValues),
                        navHostController = controller,
                        landingPageViewModel = landingPageViewModel,
                        viewModel = detailViewModel,
                        listMovieViewModel = listMovieViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}