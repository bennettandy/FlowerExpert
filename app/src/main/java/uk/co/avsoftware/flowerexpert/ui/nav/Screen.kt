package uk.co.avsoftware.flowerexpert.ui.nav

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
    data object ScreenTwo : Screen("screen_two")

}