package com.yuejianzhong.latte_ec.main.personal.order

import android.net.Uri
import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R
import kotlinx.android.synthetic.main.delegate_order_connent.*
import com.blankj.utilcode.util.ToastUtils
import com.yuejianzhong.latte_core.util.callback.CallbackManager
import com.yuejianzhong.latte_core.util.callback.CallbackType

class OrderCommentDelegate : LatteDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_order_connent

    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val mToolbar = `$`<View>(R.id.tb_order_comment)
//        mStarLayout = `$`<View>(R.id.custom_star_layout)
//        mAutoPhotoLayout = `$`<View>(R.id.custom_auto_photo_layout)
//        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0)
        top_tv_comment_commit.setOnClickListener { onClickSubmit() }
//        mAutoPhotoLayout.setFragment(this)
//        CallbackManager.getInstance()
//                .addCallback(CallbackType.ON_CROP) { args -> mAutoPhotoLayout.onCropTarget(args) }

        custom_auto_photo_layout.setFragment(this@OrderCommentDelegate)
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP) { args ->
                    custom_auto_photo_layout.onCropTarget(args as Uri?)
//                    et_order_comment.isFocusable = true
                }


    }

    private fun onClickSubmit() {
        ToastUtils.showShort("评分：" + custom_star_layout.getStarCount())
    }
}