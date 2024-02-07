package uk.co.avsoftware.flowerexpert.ui.home.mvi

sealed interface HomeViewEvent {
    data object TakePhotograph : HomeViewEvent
}