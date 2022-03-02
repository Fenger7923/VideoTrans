package com.fenger.videotrans.ui.page

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.TorchState
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.fenger.videotrans.camera.CameraUIAction
import com.fenger.videotrans.camera.CameraUtils
import com.fenger.videotrans.ui.widget.CameraPreviewView
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * @author fengerzhang
 * @date 2022/2/25 16:47
 */
@Composable
fun CameraPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    homeIndex: Int = 0,
    onImageCaptured: (Uri?) -> Unit
) {
    val context = LocalContext.current
    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_FRONT) }
    var torchState by remember { mutableStateOf(TorchState.OFF) }

    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> onImageCaptured(uri) }

    CameraPreviewView(imageCapture, lensFacing, torchState) { cameraUIAction ->
        when (cameraUIAction) {
            is CameraUIAction.OnCameraClick -> {
                CameraUtils.takePhoto(imageCapture, context, onImageCaptured)
            }
            is CameraUIAction.OnSwitchCameraClick -> {
                lensFacing =
                    if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT else CameraSelector.LENS_FACING_BACK
            }
            is CameraUIAction.OnGalleryViewClick -> {
                galleryLauncher.launch("image/*")
            }
            is CameraUIAction.OnSwitchCameraTorch -> {
                torchState = if (torchState == TorchState.OFF) TorchState.ON else TorchState.OFF
            }
        }
    }
}

suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}