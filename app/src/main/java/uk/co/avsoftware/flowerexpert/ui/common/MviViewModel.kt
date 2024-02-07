package uk.co.avsoftware.flowerexpert.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<I,E,S>(initialState: S) : ViewModel(), IntentHandler<I> {
    private val _viewEvents = MutableSharedFlow<E>()
    val viewEvents: SharedFlow<E>
        get() = _viewEvents

    private val userIntent = Channel<I>(Channel.UNLIMITED)
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S>
        get() = _state

    init {
        handleIntent()
    }

    override fun receiveIntent(intent: I) {
        viewModelScope.launch {
            userIntent.trySend(intent)
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                processIntent(it)
            }
        }
    }

    fun emitViewEvent(event: E){
        viewModelScope.launch {
            _viewEvents.emit(event)
        }
    }

    abstract fun processIntent(intent: I)
}