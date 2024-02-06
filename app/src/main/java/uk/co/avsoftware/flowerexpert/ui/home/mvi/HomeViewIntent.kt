package uk.co.avsoftware.flowerexpert.ui.home.mvi

sealed interface HomeViewIntent {
    data object TakePhotograph : HomeViewIntent
}