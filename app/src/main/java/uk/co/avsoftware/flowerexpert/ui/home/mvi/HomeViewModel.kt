package uk.co.avsoftware.flowerexpert.ui.home.mvi

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import uk.co.avsoftware.flowerexpert.domain.model.Classification
import uk.co.avsoftware.flowerexpert.ui.capture.LifecycleCameraControllerFactory
import uk.co.avsoftware.flowerexpert.ui.common.MviViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext context: Context,
    cameraControllerFactory: LifecycleCameraControllerFactory
) : MviViewModel<HomeViewIntent, HomeViewEvent, HomeUiState>(
    HomeUiState()
) {

    val cameraController: LifecycleCameraController by lazy {
        cameraControllerFactory.instance(context) {
            updateClassifications(it)
        }
    }

    override fun processIntent(intent: HomeViewIntent) {
        when (intent) {
            is HomeViewIntent.TakePhotograph -> takePhotograph()
            is HomeViewIntent.ClassifierDisabled -> showDisabled()
            is HomeViewIntent.ClassifierEnabled -> showEnabled()
        }
    }

    private fun takePhotograph() {
        Timber.d("Take photograph")
        emitViewEvent(HomeViewEvent.TakePhotograph)
    }

    private fun showDisabled() {
        Timber.d("Classifier is disabled")
        _state.value = _state.value.copy(
            classifierEnabled = false
        )
    }

    private fun showEnabled() {
        Timber.d("Classifier is enabled")
        _state.value = _state.value.copy(
            classifierEnabled = true
        )
    }

    private fun updateClassifications(classifications: List<Classification>) {
        _state.value = _state.value.copy(classifications = classifications)
    }
}

data class HomeUiState(
    val classifierEnabled: Boolean = true,
    val actionButtonVisible: Boolean = false,
    val classifications: List<Classification> = emptyList(),
)