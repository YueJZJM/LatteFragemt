package com.yuejianzhong.latte_ec.camera

import android.net.Uri
import com.yuejianzhong.latte_core.delegate.PermissionCheckDelegate
import com.yuejianzhong.latte_core.util.FileUtil


object LatteCamera {

    fun createCropFile(): Uri {
        //剪裁图片的地址
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).path)
    }

    fun start(delegate: PermissionCheckDelegate) {
        CameraHandler(delegate).beginCameraDialog()
    }
}
