package uk.co.avsoftware.pytorch

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils
import uk.co.avsoftware.common.getLocalAssetFileAsPath
import uk.co.avsoftware.pytorch.ui.theme.FlowerExpertTheme
import kotlin.math.abs


class MainPyTorchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // load image as Bitmap
        val bitmap = BitmapFactory.decodeStream(assets.open("goldfish.jpg"))


        var scaled = Bitmap.createScaledBitmap(bitmap, 224, 224, false)

        // load model
        val module: Module = Module.load(getLocalAssetFileAsPath("model.ptl"))


        // prep input Tensor
        val inputTensor: Tensor = TensorImageUtils.bitmapToFloat32Tensor(
            scaled,
            TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB
        )

        // Run Inference
        val outputTensor = module.forward(IValue.from(inputTensor)).toTensor()
        val scores = outputTensor.dataAsFloatArray

        fun indexOfMaxValue(array: FloatArray): Int {
            return array.indices.maxByOrNull { array[it] } ?: -1
        }

        // max score
        val max = indexOfMaxValue(scores)


        // obtain max value from scores and obtain class
//        var maxScore = -Float.MAX_VALUE
//        var maxScoreIdx = -1
//        for (i in 0 until scores.size) {
//            if (scores[i] > maxScore) {
//                maxScore = scores[i]
//                maxScoreIdx = i
//            }
//        }
        val className: String = IMAGENET_CLASSES[max]



        setContent {
            FlowerExpertTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(className)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlowerExpertTheme {
        Greeting("Android")
    }
}