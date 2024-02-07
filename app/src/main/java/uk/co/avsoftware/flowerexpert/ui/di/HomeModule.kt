package uk.co.avsoftware.flowerexpert.ui.di

import android.content.Context
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

//@Module
//@InstallIn(ActivityComponent::class)
//class HomeModule {
//
////    @ActivityScoped
//    @Provides
//    fun provideCameraController(@ApplicationContext applicationContext: Context): LifecycleCameraController =
//        LifecycleCameraController(applicationContext).apply {
//            setEnabledUseCases(
//                CameraController.IMAGE_CAPTURE or
//                        CameraController.VIDEO_CAPTURE
//            )
//        }
//}