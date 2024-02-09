package uk.co.avsoftware.landmarks

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import uk.co.avsoftware.flowerexpert.domain.model.Classification
import uk.co.avsoftware.landmarks.data.classifier.TfLiteLandmarkClassifier


class LandmarkImageAnalyzer(
    private val classifier: TfLiteLandmarkClassifier,
    private val onResults: (List<Classification>) -> Unit
) : ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image.toBitmap().centerCrop(321, 321)

            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)
        }
        frameSkipCounter++

        image.close()
    }
}