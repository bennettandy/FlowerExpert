package uk.co.avsoftware.flowerexpert.ui.home.mvi

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import uk.co.avsoftware.flowerexpert.ui.common.MviViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext context: Context
): MviViewModel<HomeViewIntent, HomeViewEvent, HomeUiState>(
    HomeUiState()
) {
    val cameraController: LifecycleCameraController =
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
        }

    override fun processIntent(intent: HomeViewIntent) {
        when (intent){
            is HomeViewIntent.TakePhotograph -> takePhotograph()
        }
    }

    private fun takePhotograph(){
        Timber.d("Take photograph")
        emitViewEvent(HomeViewEvent.TakePhotograph)
    }
}

data class HomeUiState( val actionButtonVisible: Boolean = false)