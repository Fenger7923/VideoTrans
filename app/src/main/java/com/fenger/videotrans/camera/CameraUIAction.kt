package com.fenger.videotrans.camera

/**
 * @author fengerzhang
 * @date 2022/3/1 18:48
 */
sealed class CameraUIAction {
    object OnCameraClick : CameraUIAction() // 拍照动作
    object OnGalleryViewClick : CameraUIAction() // 打开相册
    object OnSwitchCameraClick : CameraUIAction() // 翻转
    object OnSwitchCameraTorch: CameraUIAction() // 打开关闭闪光灯
}
