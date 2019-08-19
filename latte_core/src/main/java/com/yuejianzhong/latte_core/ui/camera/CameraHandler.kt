package com.yuejianzhong.latte_ec.camera

import android.content.Intent
import android.provider.MediaStore
import com.yuejianzhong.latte_core.util.FileUtil
import com.blankj.utilcode.util.FileUtils
import android.content.ContentValues
import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatButton
import android.view.Gravity
import android.view.View
import com.yuejianzhong.latte_core.R
import com.yuejianzhong.latte_core.delegate.PermissionCheckDelegate
import com.yuejianzhong.latte_core.ui.camera.RequestCodes
import java.io.File


class CameraHandler(private val FRAGMENT: PermissionCheckDelegate) : View.OnClickListener {

    private val DIALOG: AlertDialog = AlertDialog.Builder(FRAGMENT.context!!).create()

    private val photoName: String
        get() = FileUtil.getFileNameByTime("IMG", "jpg")

    internal fun beginCameraDialog() {
        DIALOG.show()
        val window = DIALOG.window
        if (window != null) {
            window.setContentView(R.layout.dialog_camera_panel)
            window.setGravity(Gravity.BOTTOM)
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //设置属性
            val params = window!!.attributes
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
            params.dimAmount = 0.5f
            window.attributes = params

            val cancelButton:AppCompatButton = window.findViewById(R.id.photo_dialog_btn_cancel)
            cancelButton.setOnClickListener(this)
            val takeButton:AppCompatButton = window.findViewById(R.id.photo_dialog_btn_take)
            takeButton.setOnClickListener(this)
            val nativeButton:AppCompatButton = window.findViewById(R.id.photo_dialog_btn_native)
            nativeButton.setOnClickListener(this)

        }
    }

    //头像-拍照
    private fun takePhone() {
        val currentPhotoName = photoName
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val tempFile = File(FileUtil.CAMERA_PHOTO_DIR, currentPhotoName)

        //兼容7.0及以上的写法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val contentValues = ContentValues(1)
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.path)
            val uri = FRAGMENT.context!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            //需要讲Uri路径转化为实际路径
            val realFile = FileUtils.getFileByPath(FileUtil.getRealFilePath(FRAGMENT.context, uri))
            val realUri = Uri.fromFile(realFile)
            CameraImageBean.instance.path = realUri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        } else {
            val fileUri = Uri.fromFile(tempFile)
            CameraImageBean.instance.path =fileUri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        }
        FRAGMENT.startActivityForResult(intent, RequestCodes.TAKE_PHOTO)
    }

    //头像-相册选择
    private fun pickPhoto() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        FRAGMENT.startActivityForResult(Intent.createChooser(intent, "选择获取图片的方式"), RequestCodes.PICK_PHOTO)
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.photo_dialog_btn_cancel -> DIALOG.cancel()
            R.id.photo_dialog_btn_take -> {
                takePhone()
                DIALOG.cancel()
            }
            R.id.photo_dialog_btn_native -> {
                pickPhoto()
                DIALOG.cancel()
            }
        }
    }

}
