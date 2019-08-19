package com.yuejianzhong.latte_ec.camera

import android.net.Uri

class CameraImageBean {

    var path: Uri? = null

    companion object {

        @JvmStatic
        val instance = CameraImageHolder.holder
    }

    private object CameraImageHolder{
        val holder = CameraImageBean()
    }
}
