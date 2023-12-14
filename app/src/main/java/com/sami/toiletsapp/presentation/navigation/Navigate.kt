package com.sami.toiletsapp.presentation.navigation

sealed class Navigate(val route: String) {

    sealed class Screen {

        object Home : Navigate("home_screen")

        object Details : Navigate("details_screen")
    }
}
