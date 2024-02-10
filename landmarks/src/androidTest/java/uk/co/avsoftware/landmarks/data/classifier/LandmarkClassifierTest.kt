package uk.co.avsoftware.landmarks.data.classifier

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertFalse

import org.junit.Test
import org.junit.runner.RunWith

import uk.co.avsoftware.flowerexpert.domain.model.Classification
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LandmarkClassifierTest {
    @Test
    fun testBigBen() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val sut = TfLiteLandmarkClassifier(appContext)

        // GIVEN
        val bigBen = appContext.loadBitmapFromAssets("bigben.jpg")

        // WHEN
        val classes: List<Classification> = bigBen?.let {
            sut.classify(bigBen, 0)
        } ?: emptyList()

        // THEN
        assertFalse(classes.isEmpty())
        assertEquals("Big Ben", classes[0].name)
    }

    @Test
    fun testSpinnaker() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val sut = TfLiteLandmarkClassifier(appContext)

        // GIVEN
        val bigBen = appContext.loadBitmapFromAssets("spinnaker.png")

        // WHEN
        val classes: List<Classification> = bigBen?.let {
            sut.classify(bigBen, 0)
        } ?: emptyList()

        // THEN
        assertFalse(classes.isEmpty())
        assertEquals("Spinnaker Tower", classes[0].name)
    }

    private fun Context.loadBitmapFromAssets(assetPath: String): Bitmap? {
        return try {
            val assetManager = assets
            val inputStream = assetManager.open(assetPath)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}