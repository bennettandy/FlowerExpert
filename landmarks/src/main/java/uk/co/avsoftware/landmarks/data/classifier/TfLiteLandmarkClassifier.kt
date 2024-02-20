package uk.co.avsoftware.landmarks.data.classifier

import android.content.Context
import android.graphics.Bitmap
import android.view.Surface
import dagger.hilt.android.qualifiers.ApplicationContext
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import uk.co.avsoftware.landmarks.domain.classifier.LandmarkClassifier
import uk.co.avsoftware.flowerexpert.domain.model.Classification
import javax.inject.Inject

internal class TfLiteLandmarkClassifier(
    context: Context,
    threshold: Float = 0.5f,
    maxResults: Int = 3,
    modelPath: String = "landmarks.tflite"
): LandmarkClassifier {

    private var classifier: ImageClassifier? = null

    init {
        val baseOptions = BaseOptions.builder()
            .setNumThreads(2)
            .build()
        val options = ImageClassifier.ImageClassifierOptions.builder()
            .setBaseOptions(baseOptions)
            .setMaxResults(maxResults)
            .setScoreThreshold(threshold)
            .build()

        //try {
            classifier = ImageClassifier.createFromFileAndOptions(
                context,
                modelPath,
                options
            )
//        } catch (e: IllegalStateException) {
//            e.printStackTrace()
//        }
    }

    override fun classify(bitmap: Bitmap, rotation: Int): List<Classification> {

        val imageProcessor = ImageProcessor.Builder().build()
        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))

        val imageProcessingOptions = ImageProcessingOptions.builder()
            .setOrientation(getOrientationFromRotation(rotation))
            .build()

        val results = classifier?.classify(tensorImage, imageProcessingOptions)

        return results?.flatMap { classications ->
            classications.categories.map { category ->
                Classification(
                    name = category.displayName,
                    score = category.score
                )
            }
        }?.distinctBy { it.name } ?: emptyList()
    }

    private fun getOrientationFromRotation(rotation: Int): ImageProcessingOptions.Orientation {
        return when(rotation) {
            Surface.ROTATION_270 -> ImageProcessingOptions.Orientation.BOTTOM_RIGHT
            Surface.ROTATION_90 -> ImageProcessingOptions.Orientation.TOP_LEFT
            Surface.ROTATION_180 -> ImageProcessingOptions.Orientation.RIGHT_BOTTOM
            else -> ImageProcessingOptions.Orientation.RIGHT_TOP
        }
    }
}