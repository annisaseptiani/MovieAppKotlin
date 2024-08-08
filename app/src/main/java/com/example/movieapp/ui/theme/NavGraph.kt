package com.example.movieapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.LandingPage.LandingPageScreen
import com.example.movieapp.LandingPage.LandingPageViewModel
import com.example.movieapp.ListMovieScreen
import com.example.movieapp.MovieDetail.MovieDetail
import com.example.movieapp.data.model.DetailMovieResponse

@Composable
fun NavigationGraph(modifier: Modifier, navHostController: NavHostController, landingPageViewModel: LandingPageViewModel) {
    NavHost(navController = navHostController, startDestination = Routes.Landing.routes) {
        composable(Routes.Landing.routes) {
            LandingPageScreen(viewModel = landingPageViewModel, navHostController = navHostController)
        }
        composable(Routes.ListAll.routes) {
            ListMovieScreen()
        }
        composable(Routes.Detail.routes) {
            MovieDetail()
        }

    }

}