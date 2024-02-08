package uk.co.avsoftware.flowerexpert.ui.home

import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import uk.co.avsoftware.flowerexpert.ui.common.IntentHandler
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeUiState
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeViewIntent
import uk.co.avsoftware.flowerexpert.ui.nav.Navigation

@Composable
fun MainScaffold(
    navHostController: NavHostController,
    cameraController: LifecycleCameraController,
    uiState: State<HomeUiState>,
    intentHandler: IntentHandler<HomeViewIntent>
) {

    Scaffold(
        topBar = { MainTopBar(uiState) },
        bottomBar = { MainBottomBar(navHostController) },
        floatingActionButton = {
            if (uiState.value.actionButtonVisible) {
                FloatingActionButton(onClick = {
                    intentHandler.receiveIntent(HomeViewIntent.TakePhotograph)
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    ) { innerPadding ->
        Navigation(
            navController = navHostController,
            cameraController = cameraController,
            intentHandler = intentHandler,
            uiState = uiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}