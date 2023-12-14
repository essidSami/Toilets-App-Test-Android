package com.sami.toiletsapp.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
    bottomBarPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            homeScreen(navController)
            detailsScreen(navController)
        }
    )
}

fun NavGraphBuilder.homeScreen(
    navController: NavController,
) {
    composable(
        route = Navigate.Screen.Home.route
    ) {
        val activity = (LocalContext.current as? Activity)

        BackHandler(true) {
            activity?.finish()
        }

    }
}

fun NavGraphBuilder.detailsScreen(navController: NavController) {
    composable(
        route = Navigate.Screen.Details.route
    ) {

    }
}