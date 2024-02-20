package uk.co.avsoftware.flowerexpert

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import uk.co.avsoftware.flowerexpert.ui.capture.CameraHelper
import uk.co.avsoftware.flowerexpert.ui.home.MainScaffold
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeViewEvent
import uk.co.avsoftware.flowerexpert.ui.home.mvi.HomeViewModel
import uk.co.avsoftware.flowerexpert.ui.theme.FlowerExpertTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val cameraHelper: CameraHelper by lazy { CameraHelper(this) }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // demo code
        asyncDemoCode()

        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewEvents.collect(::handleHomeViewEvent)
            }
        }

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

    private fun asyncDemoCode(){

        suspend fun request1(): String {
            delay(3000)
            return "answer 1"
        }

        suspend fun request2(): String {
            delay(3000)
            return "answer 2"
        }

        lifecycleScope.launch {
            val deferred1: Deferred<String> = async { request1() }
            val deferred2: Deferred<String> = async { request2() }

            Timber.d("Answer 1: ${deferred1.await()}")
            Timber.d("Answer 2: ${deferred2.await()}")
        }
    }

    private fun handleHomeViewEvent(homeViewEvent: HomeViewEvent){
        when (homeViewEvent) {
            is HomeViewEvent.TakePhotograph -> cameraHelper.takePhoto(viewModel.cameraController) { bitmap ->
                Timber.d("Got Bitmap H:${bitmap.height}, W:${bitmap.width}")
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

}
