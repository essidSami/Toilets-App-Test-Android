package com.sami.toiletsapp.presentation.navigation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.toilets.domain.model.Toilet
import app.toilets.presentation.details.DetailsScreen
import app.toilets.presentation.home.AgainPermissionView
import app.toilets.presentation.home.HomeScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun AppNavigation(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        builder = {
            homeScreen(navController = navController)
            detailsScreen(navController)
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
fun NavGraphBuilder.homeScreen(
    navController: NavController
) {
    composable(
        route = Navigate.Screen.Home.route
    ) {
        val activity = (LocalContext.current as? Activity)
        val locationPermissionState =
            rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

        BackHandler(true) {
            activity?.finish()
        }

        when {
            //Permission is granted
            locationPermissionState.status.isGranted -> {
                HomeScreen(
                    viewModel = hiltViewModel()
                ) {
                    navController.currentBackStackEntry?.savedStateHandle?.set("toilet", it)
                    navController.navigate(Navigate.Screen.Details.route)
                }
            }
            //Permission not granted
            else -> {
                LaunchedEffect(Unit) {
                    // Request the permission
                    locationPermissionState.launchPermissionRequest()
                }
                activity?.let {
                    AgainPermissionView(
                        onOkClick = {
                            val intent =
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.fromParts("package", it.packageName, null)
                                }
                            it.startActivity(intent)
                        }
                    )
                }
            }

        }

    }
}

fun NavGraphBuilder.detailsScreen(navController: NavController) {
    composable(
        route = Navigate.Screen.Details.route
    ) {
        navController.previousBackStackEntry?.savedStateHandle?.get<Toilet>("toilet")
            ?.let { toiletInfo ->
                DetailsScreen(toiletInfo = toiletInfo) {
                    navController.popBackStack()
                }
            }
    }
}

