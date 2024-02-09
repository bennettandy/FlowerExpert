package uk.co.avsoftware.common

import android.content.Context
import java.io.File
import java.io.FileOutputStream

fun Context.getLocalAssetFileAsPath(assetName: String): String {
    val file = File(filesDir, assetName)
    if (file.exists() && file.length() > 0) {
        return file.absolutePath
    }

    assets.open(assetName).use { `is` ->
        FileOutputStream(file).use { os ->
            val buffer = ByteArray(4 * 1024)
            var read: Int
            while (`is`.read(buffer).also { read = it } != -1) {
                os.write(buffer, 0, read)
            }
            os.flush()
        }
        return file.absolutePath
    }
}