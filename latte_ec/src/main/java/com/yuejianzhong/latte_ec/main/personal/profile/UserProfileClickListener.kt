package com.yuejianzhong.latte_ec.main.personal.profile

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.SimpleClickListener
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.main.personal.list.ListBean
import android.content.DialogInterface
import android.net.Uri
import android.support.annotation.NonNull
import android.support.v7.app.AlertDialog
import android.widget.TextView
import com.alibaba.fastjson.JSON
import com.bumptech.glide.Glide
import com.yuejianzhong.latte_core.log.LogUtils
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_core.util.callback.CallbackManager
import com.yuejianzhong.latte_core.util.callback.CallbackType
import com.yuejianzhong.latte_core.util.callback.IGlobalCallback
import com.yuejianzhong.latte_ec.R
import com.yuejianzhong.latte_ec.data.DateDialogUtil


class UserProfileClickListener constructor(val delegate: LatteDelegate) : SimpleClickListener() {

    private val mGenders = arrayOf("男", "女", "保密")

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    }

    override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    }

    override fun onItemChildLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val bean = baseQuickAdapter.data[position] as ListBean
        when (bean.id) {
            1 -> {
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP) {
                            com.blankj.utilcode.util.LogUtils.d(it)
                        }
                delegate.startCamerWithCheck()
                //开始打开相机或者选择图片 =>  然后裁剪图片  => 产生回调(图片存储的地址URI)
                //1.保存一个回调，给activity for result
//                CallbackManager.getInstance()
//                        .addCallback(CallbackType.ON_CROP, object : IGlobalCallback<Uri>() {
//                            fun executeCallback(@NonNull args: Uri) {
//                                LogUtils.d("ON_CROP", args)
//                                //首先显示裁剪后存储好的图片作为头像
//                                val avatar = view.findViewById<View>(R.id.img_arrow_avatar)
//                                Glide.with(delegate)
//                                        .load(args)
//                                        .into(avatar)
//                                //上传图片到服务器
//                                RestClient.builder()
//                                        .url(UploadConfig.UPLOAD_IMG)
//                                        .loader(delegate.getContext())
//                                        .file(args.getPath())
//                                        .success(ISuccess { response ->
//                                            //图片上传返回结果值
//                                            LogUtils.d("ON_CROP_UPLOAD", response)
//                                            val path = JSON.parseObject(response).getJSONObject("result")
//                                                    .getString("path")
//
//                                            //通知服务器 更新信息
//                                            RestClient.builder()
//                                                    .url("user_profile.php")
//                                                    .params("avatar", path)
//                                                    .loader(delegate.getContext())
//                                                    .success {
//                                                        //获取更新后的用户信息，然后更新本地数据库
//                                                        //没有本地数据的APP，每次打开APP都请求API，获取信息
//                                                    }
//                                                    .build()
//                                                    .post()
//                                        })
//                                        .build()
//                                        .upload()
//                            }
//                        })
//                delegate.startCameraWithCheck()
            }
            2 -> {
                val nameFragment = bean.fragment
                delegate.supportDelegate.start(nameFragment)
            }
            3 -> getGenderDialog(DialogInterface.OnClickListener { dialog, which ->
                val textView = view.findViewById<TextView>(R.id.tv_arrow_value)
                textView.text = mGenders[which]
                dialog.cancel()
            })
            4 -> {
                val dateDialogUtil = DateDialogUtil()
                dateDialogUtil.setDateListener(object : DateDialogUtil.IDateListener {
                    override fun onDateChange(date: String) {
                        val textView = view.findViewById<TextView>(R.id.tv_arrow_value)
                        textView.text = date
                    }
                })
                dateDialogUtil.showDialog(delegate.context!!)
            }
            else -> {
            }
        }
    }


    private fun getGenderDialog(listener: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(delegate.context!!)
        builder.setSingleChoiceItems(mGenders, 0, listener)
        builder.show()
    }

}