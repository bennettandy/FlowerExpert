package uk.co.avsoftware.landmarks.domain.classifier

interface LandmarkClassifierFactory {
    fun provideLandmarkClassifier(): LandmarkClassifier?
}