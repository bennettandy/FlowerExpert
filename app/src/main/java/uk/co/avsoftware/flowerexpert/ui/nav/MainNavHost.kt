package uk.co.avsoftware.flowerexpert.ui.nav

import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeUiState
import uk.co.avsoftware.flowerexpert.ui.home.PageOne
import uk.co.avsoftware.flowerexpert.ui.home.PageTwo

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    cameraController: LifecycleCameraController,
    uiState: State<HomeUiState>,
    modifier: Modifier = Modifier
) {
    NavHost(modifier = modifier, navController = navHostController, startDestination = "page_one") {
        composable("page_one") {
            PageOne(navHostController, modifier)
        }
        composable("page_two") {
            PageTwo(navHostController, cameraController, modifier)
        }
    }
}