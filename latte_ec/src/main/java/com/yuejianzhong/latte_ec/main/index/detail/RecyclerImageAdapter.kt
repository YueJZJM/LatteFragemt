package com.yuejianzhong.latte_ec.main.index.detail

import com.bumptech.glide.Glide
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.blankj.utilcode.util.SnackbarUtils.getView
import android.support.v7.widget.AppCompatImageView
import com.yuejianzhong.latte_core.ui.recycler.ItemType
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.yuejianzhong.latte_core.ui.recycler.MultipleViewHolder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.yuejianzhong.latte_ec.R


class RecyclerImageAdapter(data: List<MultipleItemEntity>) : BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>(data) {


    init {
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_image)
    }

    override fun convert(helper: MultipleViewHolder, item: MultipleItemEntity) {
        val type = helper.itemViewType
        when (type) {
            ItemType.SINGLE_BIG_IMAGE -> {
                val imageView = helper.getView<AppCompatImageView>(R.id.image_rv_item)
                val url = item.getField(MultipleFields.IMAGE_URL)
                Glide.with(mContext)
                        .load(url)
                        .apply(OPTIONS)
                        .into(imageView)
            }
            else -> {
            }
        }
    }

    companion object {

        private val OPTIONS = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()
    }
}
