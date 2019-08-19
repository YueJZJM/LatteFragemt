package com.yuejianzhong.latte_ec.main.personal.list

import android.graphics.drawable.Drawable
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuejianzhong.latte_ec.R
import android.support.v7.widget.SwitchCompat
import android.view.View
import android.widget.ImageView
import retrofit2.http.OPTIONS
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions





class ListAdapter (data:List<ListBean>): BaseMultiItemQuickAdapter<ListBean, BaseViewHolder>(data) {

    private val OPTIONS = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop()


    init {
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout)
        addItemType(ListItemType.ITEM_AVATAR,R.layout.array_item_avater)
    }

    override fun convert(helper: BaseViewHolder, item: ListBean) {
        when (helper.itemViewType) {
            ListItemType.ITEM_NORMAL -> {
                helper.setText(R.id.tv_arrow_text, item.text)
                helper.setText(R.id.tv_arrow_value, item.value)
            }
            ListItemType.ITEM_AVATAR -> Glide.with(mContext)
                    .load(item.imageUrl)
                    .apply(OPTIONS)
                    .into(helper.getView<View>(R.id.img_arrow_avatar) as ImageView)
            ListItemType.ITEM_SWITCH -> {
                helper.setText(R.id.tv_arrow_switch_text, item.text)
                val switchCompat = helper.getView<SwitchCompat>(R.id.list_item_switch)
                switchCompat.isChecked = true
                switchCompat.setOnCheckedChangeListener(item.getmOnCheckedChangeListener())
            }
            else -> {
            }
        }
    }

}