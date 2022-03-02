package com.fenger.videotrans.camera

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import java.io.File

/**
 * @author fengerzhang
 * @date 2022/3/2 14:48
 */
object CameraUtils {
    fun takePhoto(
        imageCapture: ImageCapture,
        context: Context,
        onImageCaptured: (Uri?) -> Unit
    ) {
        val file = File(context.getExternalFilesDir( Environment.DIRECTORY_MOVIES).toString() + "/" + System.currentTimeMillis() + ".png")
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(context), object :
            ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                onImageCaptured(outputFileResults.savedUri)
                Toast.makeText(context, "存储相片成功", Toast.LENGTH_SHORT).show()
                }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(context, "存储相片失败", Toast.LENGTH_SHORT).show()
            }
        })
    }
}