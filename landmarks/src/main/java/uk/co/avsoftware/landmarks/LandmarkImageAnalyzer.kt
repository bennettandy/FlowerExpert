package uk.co.avsoftware.landmarks

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import uk.co.avsoftware.flowerexpert.domain.model.Classification
import uk.co.avsoftware.landmarks.data.classifier.TfLiteLandmarkClassifier
import uk.co.avsoftware.landmarks.domain.classifier.LandmarkClassifier


class LandmarkImageAnalyzer(
    private val classifier: LandmarkClassifier,
    private val onResults: (List<Classification>) -> Unit
) : ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter % 120 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image.toBitmap().centerCrop(224, 224)

            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)
        }
        frameSkipCounter++

        image.close()
    }
}