package com.sami.toiletsapp.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.toilets.domain.model.Toilet
import app.toilets.presentation.details.DetailsScreen
import app.toilets.presentation.home.HomeScreen
import app.toilets.presentation.home.HomeViewModel

@Composable
fun AppNavigation(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String,
    viewModel: HomeViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        builder = {
            homeScreen(navController = navController, viewModel = viewModel)
            detailsScreen(navController)
        }
    )
}

fun NavGraphBuilder.homeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    composable(
        route = Navigate.Screen.Home.route
    ) {
        val activity = (LocalContext.current as? Activity)

        BackHandler(true) {
            activity?.finish()
        }
        HomeScreen(
            viewModel = viewModel
        ) {
            navController.currentBackStackEntry?.savedStateHandle?.set("toilet", it)
            navController.navigate(Navigate.Screen.Details.route)
        }
    }
}

fun NavGraphBuilder.detailsScreen(navController: NavController) {
    composable(
        route = Navigate.Screen.Details.route
    ) {
        navController.previousBackStackEntry?.savedStateHandle?.get<Toilet>("toilet")
            ?.let { toiletInfo ->
                DetailsScreen(toiletInfo = toiletInfo){
                    navController.popBackStack()
                }
            }
    }
}