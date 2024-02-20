package uk.co.avsoftware.landmarks.domain.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.co.avsoftware.landmarks.data.classifier.TfLiteLandmarkClassifier
import uk.co.avsoftware.landmarks.domain.classifier.LandmarkClassifier
import uk.co.avsoftware.landmarks.domain.classifier.LandmarkClassifierFactory
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideLandmarkClassifierFactory(
        @ApplicationContext context: Context,
    ): LandmarkClassifierFactory {
        return object : LandmarkClassifierFactory {
            override fun provideLandmarkClassifier(): LandmarkClassifier? {
                return try {
                    TfLiteLandmarkClassifier(context = context)
                } catch (e: Throwable) {
                    null
                }
            }
        }
    }

}