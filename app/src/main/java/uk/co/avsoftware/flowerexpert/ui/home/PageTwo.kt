package uk.co.avsoftware.flowerexpert.ui.home

import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uk.co.avsoftware.flowerexpert.ui.capture.CameraPreview
import uk.co.avsoftware.flowerexpert.ui.capture.SwitchCameraIconButton
import uk.co.avsoftware.flowerexpert.R
import uk.co.avsoftware.flowerexpert.ui.common.IntentHandler
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeViewIntent

@Composable
fun PageTwo(
    intentHandler: IntentHandler<HomeViewIntent>,
    cameraController: LifecycleCameraController,
    modifier: Modifier = Modifier
) {

    CameraPreview(
        controller = cameraController,
        modifier = modifier
            .fillMaxSize()
    )

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        SwitchCameraIconButton(cameraController)

        Box(modifier = Modifier.fillMaxSize()){
            Button(onClick = { intentHandler.receiveIntent(HomeViewIntent.TakePhotograph) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 26.dp)
            ) {
                Text(stringResource(id = R.string.take_photograph_button))
            }
        }
    }
}