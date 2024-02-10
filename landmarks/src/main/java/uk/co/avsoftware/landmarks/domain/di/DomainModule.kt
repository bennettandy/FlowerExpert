package uk.co.avsoftware.landmarks.domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.co.avsoftware.landmarks.data.classifier.TfLiteLandmarkClassifier
import uk.co.avsoftware.landmarks.domain.classifier.LandmarkClassifier

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideLandmarkClassifier(@ApplicationContext context: Context): LandmarkClassifier =
        TfLiteLandmarkClassifier(context = context)
}