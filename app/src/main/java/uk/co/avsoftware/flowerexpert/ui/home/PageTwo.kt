package uk.co.avsoftware.flowerexpert.ui.home

import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uk.co.avsoftware.flowerexpert.ui.capture.CameraPreview

@Composable
fun PageTwo(
    navHostController: NavHostController,
    cameraController: LifecycleCameraController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
    ) {
//        Text(
//            modifier = Modifier.padding(8.dp),
//            text =
//            """
//                    PAGE TWO
//
//                    XX
//
//                """.trimIndent(),
//        )

        CameraPreview(
            controller = cameraController,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Button(onClick = { navHostController.popBackStack() }) {
            Text("Back")
        }
    }
}