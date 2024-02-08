package uk.co.avsoftware.flowerexpert.domain

import android.graphics.Bitmap

import java.io.ByteArrayOutputStream

class BitmapToByteArrayConverter {
    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }
}