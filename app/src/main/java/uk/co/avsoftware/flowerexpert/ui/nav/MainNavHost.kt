package uk.co.avsoftware.flowerexpert.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.co.avsoftware.flowerexpert.ui.home.PageOne
import uk.co.avsoftware.flowerexpert.ui.home.PageTwo

@Composable
fun MainNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(modifier = modifier, navController = navHostController, startDestination = "page_one") {
        composable("page_one") {
            PageOne(navHostController)
        }
        composable("page_two") {
            PageTwo(navHostController)
        }
    }
}