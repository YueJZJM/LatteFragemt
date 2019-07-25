package com.yuejianzhong.latte_core.ui.banner

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.view.View
import com.bigkoo.convenientbanner.holder.Holder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yuejianzhong.latte_core.R

class ImageHolder :Holder<String>{

    private lateinit var mImageView: AppCompatImageView;

    override fun UpdateUI(context: Context?, position: Int, data: String?) {
        Glide.with(context!!).load(data)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .into(mImageView)
    }

    override fun createView(context: Context?): View {
        mImageView = AppCompatImageView(context)
        return mImageView
    }

}