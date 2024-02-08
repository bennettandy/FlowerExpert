package uk.co.avsoftware.flowerexpert.domain.classifier

import android.graphics.Bitmap
import uk.co.avsoftware.flowerexpert.domain.model.Classification

interface LandmarkClassifyInteractor {
    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}