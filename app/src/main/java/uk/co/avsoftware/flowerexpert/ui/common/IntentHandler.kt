package uk.co.avsoftware.flowerexpert.ui.common

interface IntentHandler<I> {
    fun receiveIntent(intent: I)
}