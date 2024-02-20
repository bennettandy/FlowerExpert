package uk.co.avsoftware.flowerexpert.ui.home.mvi

sealed interface HomeViewIntent {
    data class ClassifierDisabled(val reason: String) : HomeViewIntent
    data object ClassifierEnabled : HomeViewIntent
    data object TakePhotograph : HomeViewIntent
}