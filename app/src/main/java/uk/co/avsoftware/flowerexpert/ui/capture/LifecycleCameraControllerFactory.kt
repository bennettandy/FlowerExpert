package uk.co.avsoftware.flowerexpert.ui.capture

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import uk.co.avsoftware.flowerexpert.domain.model.Classification
import uk.co.avsoftware.landmarks.LandmarkImageAnalyzer
import uk.co.avsoftware.landmarks.domain.classifier.LandmarkClassifierFactory
import javax.inject.Inject

class LifecycleCameraControllerFactory @Inject constructor(
    private val landmarkClassifierFactory: LandmarkClassifierFactory
) {

    private fun createLifecycleCamera(
        context: Context,
        onResults: (List<Classification>) -> Unit
    ): LifecycleCameraController {
        return LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)

            // set image classifier on camera controller
            landmarkClassifierFactory.provideLandmarkClassifier()?.let { classifier ->
                setImageAnalysisAnalyzer(
                    ContextCompat.getMainExecutor(context),
                    LandmarkImageAnalyzer(
                        classifier,
                        onResults
                    )
                )
            }
        }
    }

    fun instance(
        context: Context,
        onResults: (List<Classification>) -> Unit
    ): LifecycleCameraController {
        return createLifecycleCamera(context, onResults)
    }
}