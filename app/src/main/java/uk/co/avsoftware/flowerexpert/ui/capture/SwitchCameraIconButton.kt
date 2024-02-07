package uk.co.avsoftware.flowerexpert.ui.capture

import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uk.co.avsoftware.flowerexpert.R

@Composable
fun SwitchCameraIconButton(controller: CameraController, modifier: Modifier = Modifier) {
    IconButton(
        onClick = {
            controller.cameraSelector =
                if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                    CameraSelector.DEFAULT_FRONT_CAMERA
                } else CameraSelector.DEFAULT_BACK_CAMERA
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Cameraswitch,
            contentDescription = stringResource(id = R.string.switch_camera_content_desc)
        )
    }
}