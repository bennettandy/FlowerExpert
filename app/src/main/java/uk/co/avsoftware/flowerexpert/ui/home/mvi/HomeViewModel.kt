package uk.co.avsoftware.flowerexpert.ui.home.mvi

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import uk.co.avsoftware.flowerexpert.domain.model.Classification
import uk.co.avsoftware.flowerexpert.ui.common.MviViewModel
import uk.co.avsoftware.landmarks.LandmarkImageAnalyzer
import uk.co.avsoftware.landmarks.domain.classifier.LandmarkClassifier
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext context: Context,
    classifier: LandmarkClassifier
): MviViewModel<HomeViewIntent, HomeViewEvent, HomeUiState>(
    HomeUiState()
) {

    val cameraController: LifecycleCameraController =
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context),
                LandmarkImageAnalyzer(
                    classifier
                ) { classifications ->
                    Timber.d("${classifications.size}")
                    updateClassifications(classifications)
                }
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

    private fun updateClassifications(classifications: List<Classification>){
        _state.value = _state.value.copy(classifications = classifications)
    }
}

data class HomeUiState(
    val actionButtonVisible: Boolean = false,
    val classifications: List<Classification> = emptyList()
)