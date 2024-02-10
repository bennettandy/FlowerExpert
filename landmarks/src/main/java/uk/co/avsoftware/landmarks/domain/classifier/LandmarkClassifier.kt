package uk.co.avsoftware.landmarks.domain.classifier

import android.graphics.Bitmap
import uk.co.avsoftware.flowerexpert.domain.model.Classification

interface LandmarkClassifier {
    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}