package uk.co.avsoftware.flowerexpert.domain

import android.graphics.Bitmap
import org.pytorch.Tensor
import org.pytorch.torchvision.TensorImageUtils

class BitmapToTensorInteractor {
    fun convertToTensor(bitmap: Bitmap): Tensor {
        val tensor = TensorImageUtils.bitmapToFloat32Tensor(bitmap,
            TensorImageUtils.TORCHVISION_NORM_MEAN_RGB, TensorImageUtils.TORCHVISION_NORM_STD_RGB)
        return tensor
    }
}