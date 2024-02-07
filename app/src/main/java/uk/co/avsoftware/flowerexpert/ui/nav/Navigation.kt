package uk.co.avsoftware.flowerexpert.ui.nav

import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.co.avsoftware.flowerexpert.ui.common.IntentHandler
import uk.co.avsoftware.flowerexpert.ui.home.PageOne
import uk.co.avsoftware.flowerexpert.ui.home.PageTwo
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeUiState
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeViewIntent

@Composable
fun Navigation(
    navController: NavHostController,
    cameraController: LifecycleCameraController,
    intentHandler: IntentHandler<HomeViewIntent>,
    uiState: State<HomeUiState>,
    modifier: Modifier = Modifier
) {
    NavHost(modifier = modifier, navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            PageOne(navController)
        }
        composable(Screen.ScreenTwo.route) {
            PageTwo(intentHandler, cameraController)
        }
    }
}