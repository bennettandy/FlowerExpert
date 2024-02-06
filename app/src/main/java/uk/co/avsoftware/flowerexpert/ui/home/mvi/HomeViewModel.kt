package uk.co.avsoftware.flowerexpert.ui.home.mvi

import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import uk.co.avsoftware.flowerexpert.ui.common.MviViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): MviViewModel<HomeViewIntent, HomeViewEvent, HomeUiState>(
    HomeUiState()
) {
    override fun processIntent(intent: HomeViewIntent) {
        when (intent){
            is HomeViewIntent.TakePhotograph -> takePhotograph()
        }
    }

    private fun takePhotograph(){
        Timber.d("Take photograph")
    }
}

data class HomeUiState( val active: Boolean = true)