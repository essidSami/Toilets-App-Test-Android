package com.sami.toiletsapp.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import app.toilets.presentation.home.HomeViewModel
import com.sami.toiletsapp.presentation.navigation.AppNavigation
import com.sami.toiletsapp.presentation.navigation.Navigate

@Composable
internal fun MainScreen(viewModel: HomeViewModel, onLauncherFinished: () -> Unit) {
    val navController = rememberNavController()
    navController.navigatorProvider

    val screen = Navigate.Screen.Home.route

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues: PaddingValues ->
        AppNavigation(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = screen,
            viewModel = viewModel
        )
    }
    onLauncherFinished()
}