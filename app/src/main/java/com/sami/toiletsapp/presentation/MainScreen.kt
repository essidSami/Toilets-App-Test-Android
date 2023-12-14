package com.sami.toiletsapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import app.toilets.compose.theme.BottomSheetShape
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.sami.toiletsapp.presentation.navigation.AppNavigation
import com.sami.toiletsapp.presentation.navigation.Navigate

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
internal fun MainScreen(onLauncherFinished: () -> Unit) {
    val navController = rememberNavController()
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    val screen = Navigate.Screen.Home.route

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = BottomSheetShape,
        sheetBackgroundColor = Color.Transparent
    ) {
        Scaffold(
            containerColor = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding()
        ) {
            AppNavigation(
                navController = navController,
                startDestination = screen,
                bottomBarPadding = it
            )
        }
    }
    onLauncherFinished()
}