package com.example.movieapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.LandingPage.LandingPageScreen
import com.example.movieapp.LandingPage.LandingPageViewModel
import com.example.movieapp.ListAllMovie.ListMovieScreen
import com.example.movieapp.ListAllMovie.ListMovieViewModel
import com.example.movieapp.MovieDetail.MovieDetail
import com.example.movieapp.MovieDetail.MovieDetailViewModel

@Composable
fun NavigationGraph(modifier: Modifier, navHostController: NavHostController,
                    landingPageViewModel: LandingPageViewModel,
                    viewModel: MovieDetailViewModel,
                    listMovieViewModel: ListMovieViewModel) {
    NavHost(navController = navHostController, startDestination = Routes.Landing.routes) {
        composable(Routes.Landing.routes) {
            LandingPageScreen(viewModel = landingPageViewModel, navHostController = navHostController)
        }
        composable("${Routes.ListAll.routes}/{types}",
            arguments = listOf(navArgument(name = "types") {
                type = NavType.StringType
            })
        ) {
            val types = it.arguments?.getString("types")
            ListMovieScreen(type = types!!, navHostController = navHostController,
                viewModel = listMovieViewModel)
        }
        composable("${Routes.Detail.routes}/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.StringType
            })
        ) {
            val movieId = it.arguments?.getString("id")
            MovieDetail(viewModel = viewModel, navHostController = navHostController,
                id = movieId!!.toInt())
        }

    }

}