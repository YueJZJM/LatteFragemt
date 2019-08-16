package com.yuejianzhong.latte_ec.main.personal.order

import com.bumptech.glide.Glide
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.blankj.utilcode.util.SnackbarUtils.getView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.AppCompatImageView
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.yuejianzhong.latte_core.ui.recycler.MultipleViewHolder
import android.annotation.SuppressLint
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.yuejianzhong.latte_ec.R

class OrderListAdapter(data: List<MultipleItemEntity>) : BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>(data) {

    init {
        addItemType(OrderListItemType.ITEM_ORDER_LIST, R.layout.item_order_list)
    }

    @SuppressLint("SetTextI18n")
    override fun convert(holder: MultipleViewHolder, entity: MultipleItemEntity) {
        when (holder.itemViewType) {
            OrderListItemType.ITEM_ORDER_LIST -> {
                val imageView = holder.getView<AppCompatImageView>(R.id.image_order_list)
                val title = holder.getView<AppCompatTextView>(R.id.tv_order_list_title)
                val price = holder.getView<AppCompatTextView>(R.id.tv_order_list_price)
                val time = holder.getView<AppCompatTextView>(R.id.tv_order_list_time)

                val titleVal = entity.getField(MultipleFields.TITLE) as String
                val timeVal = entity.getField(OrderItemFields.TIME) as String
                val priceVal = entity.getField(OrderItemFields.PRICE)!! as Double
                val imageUrl = entity.getField(MultipleFields.IMAGE_URL)

                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(OPTIONS)
                        .into(imageView)

                title.text = titleVal
                price.text = "价格：$priceVal"
                time.text = timeVal
            }
            else -> {
            }
        }
    }

    companion object {

        private val OPTIONS = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .dontAnimate()
    }
}
