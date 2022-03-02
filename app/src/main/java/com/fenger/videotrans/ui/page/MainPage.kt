package com.fenger.videotrans.ui.page

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

/**
 * @author fengerzhang
 * @date 2022/2/25 16:03
 */

@ExperimentalMaterialApi
@ExperimentalMaterialNavigationApi
@ExperimentalPermissionsApi
@Composable
fun MainPage() {
    // 先申请相机权限
    val cameraPermissionState =
        rememberPermissionState(permission = Manifest.permission.CAMERA)

    when (cameraPermissionState.status) {
        PermissionStatus.Granted -> {
            // 实际的相机
            NavPage()
        }
        is PermissionStatus.Denied -> {
            val textToShow =
                if ((cameraPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                    "由于该应用必须要允许使用相机权限，\n请点击再次申请并同意使用相机权限"
                } else {
                    "请点击使用相机权限"
                }
            Box(modifier = Modifier
                .fillMaxSize()) {
                Text(text = textToShow, modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        cameraPermissionState.launchPermissionRequest()
                    })
            }
        }
    }
}