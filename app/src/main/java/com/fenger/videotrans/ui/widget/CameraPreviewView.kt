package com.fenger.videotrans.ui.widget

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.fenger.videotrans.R
import com.fenger.videotrans.camera.CameraUIAction
import com.fenger.videotrans.ui.ClickButton
import com.fenger.videotrans.ui.page.getCameraProvider

/**
 * @author fengerzhang
 * @date 2022/2/25 17:09
 */

@Composable
fun CameraPreviewView(
    imageCapture: ImageCapture,
    lensFacing: Int = CameraSelector.LENS_FACING_BACK,
    cameraUIAction: (CameraUIAction) -> Unit
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    val previewView = remember { PreviewView(context) }
    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize()) {

        }
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 15.dp)
        ) {
            ClickButton(
                title = "翻转",
                icon = R.drawable.camera_overturn,
            ) {
                cameraUIAction.invoke(CameraUIAction.OnSwitchCameraClick)
            }

            ClickButton(
                title = "相册",
                icon = R.drawable.camera_overturn,
            ) {
                cameraUIAction.invoke(CameraUIAction.OnGalleryViewClick)
            }

            ClickButton(
                title = "拍照",
                icon = R.drawable.camera_overturn,
            ) {
                cameraUIAction.invoke(CameraUIAction.OnCameraClick)
            }
            ClickButton(
                title = "闪光灯",
                icon = R.drawable.camera_overturn,
            ) {
                cameraUIAction.invoke(CameraUIAction.OnSwitchCameraTorch)
            }
        }
    }
}