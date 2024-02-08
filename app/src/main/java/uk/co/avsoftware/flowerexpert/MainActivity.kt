package uk.co.avsoftware.flowerexpert

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uk.co.avsoftware.flowerexpert.ui.capture.CameraHelper
import uk.co.avsoftware.flowerexpert.ui.home.MainScaffold
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeViewModel
import uk.co.avsoftware.flowerexpert.ui.theme.FlowerExpertTheme
import java.io.File
import java.io.FileOutputStream


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val cameraHelper = CameraHelper(this)

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.viewEvents.collect { homeViewEvent ->
//                    when (homeViewEvent) {
//                        is HomeViewEvent.TakePhotograph -> cameraHelper.takePhoto(viewModel.cameraController) { bitmap ->
//                            Timber.d("Got Bitmap H:${bitmap.height}, W:${bitmap.width}")
//                            val tensorInteractor = BitmapToTensorInteractor()
//                            val tensor = tensorInteractor.convertToTensor(bitmap)
//                            Timber.d("Tensor Shape.size: ${tensor.shape().size}")
//                        }
//                    }
//                }
//            }
//        }

        setContent {
            val navController: NavHostController = rememberNavController()
            FlowerExpertTheme {
                MainScaffold(
                    navController,
                    viewModel.cameraController,
                    uiState = viewModel.state.collectAsState(),
                    intentHandler = viewModel
                )
            }
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        )
    }


    fun getAssetFile(assetName: String): String {
        val file: File = File(filesDir, assetName)
        if (file.exists() && file.length() > 0) {
            return file.absolutePath
        }

        assets.open(assetName).use { `is` ->
            FileOutputStream(file).use { os ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (`is`.read(buffer).also { read = it } != -1) {
                    os.write(buffer, 0, read)
                }
                os.flush()
            }
            return file.absolutePath
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

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FlowerExpertTheme {
//        MainScaffold(NavHostController(LocalContext.current))
//    }
//}