package com.yuejianzhong.latte_ec.main.cart

import android.graphics.Color
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_core.net.RestClient
import android.view.Gravity
import com.yuejianzhong.latte_core.app.Latte
import android.support.v4.content.ContextCompat
import com.bumptech.glide.Glide
import com.joanzapata.iconify.widget.IconTextView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.widget.Toast
import com.blankj.utilcode.util.ToastUtils
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.yuejianzhong.latte_core.ui.recycler.MultipleViewHolder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.yuejianzhong.latte_ec.R


class ShopCartAdapter(data: List<MultipleItemEntity>) : BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>(data) {

    private var mIsSelectedAll = true
    private var mCartItemListener: ICartItemListener? = null
    var totalPrice = 0.00
        private set

    init {
        //初始化总价
        for (entity in data) {
            val price = entity.getField(ShopCartItemFields.PRICE)!! as Double
            val count = entity.getField(ShopCartItemFields.COUNT)!! as Int
            val total = price!! * count!!
            totalPrice += total
        }
        //添加购物类型布局 只包含一种
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart)
    }

    fun setIsSelectedAll(isSelectedAll: Boolean) {
        this.mIsSelectedAll = isSelectedAll
    }

    fun setCartItemListener(listener: ICartItemListener) {
        this.mCartItemListener = listener
    }

    override fun convert(helper: MultipleViewHolder, item: MultipleItemEntity) {
        when (helper.itemViewType) {
            ShopCartItemType.SHOP_CART_ITEM -> {
                //取出所有值
                val id = item.getField(MultipleFields.ID)!! as Int
                val thumb = item.getField(MultipleFields.IMAGE_URL)
                val title = item.getField(ShopCartItemFields.TITLE) as String
                val desc = item.getField(ShopCartItemFields.DESC) as String
                val count = item.getField(ShopCartItemFields.COUNT)!! as Int
                val price = item.getField(ShopCartItemFields.PRICE)!! as Double
                //取出所有控件
                val imgThumb = helper.getView<AppCompatImageView>(R.id.image_item_shop_cart)
                val tvTitle = helper.getView<AppCompatTextView>(R.id.tv_item_shop_cart_title)
                val tvDesc = helper.getView<AppCompatTextView>(R.id.tv_item_shop_cart_desc)
                val tvPrice = helper.getView<AppCompatTextView>(R.id.tv_item_shop_cart_price)
                val iconMinus = helper.getView<IconTextView>(R.id.icon_item_minus)
                val iconPlus = helper.getView<IconTextView>(R.id.icon_item_plus)
                val tvCount = helper.getView<AppCompatTextView>(R.id.tv_item_shop_cart_count)
                val iconIsSelected = helper.getView<IconTextView>(R.id.icon_item_shop_cart)

                //赋值
                tvTitle.text = title
                tvDesc.text = desc
                tvCount.text = count.toString()
                tvPrice.text = price.toString()
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb)

                //在左侧勾勾渲染之前改变全选与否状态
                item.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll)
                val isSelected = item.getField(ShopCartItemFields.IS_SELECTED)!!as Boolean
                //根据数据状态显示左侧勾勾
                if (isSelected) {
                    iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main))
                } else {
                    iconIsSelected.setTextColor(Color.GRAY)
                }
                //添加左侧勾勾点击事件
                iconIsSelected.setOnClickListener {
                    val currentSelected = item.getField(ShopCartItemFields.IS_SELECTED)!! as Boolean
                    if (currentSelected) {
                        iconIsSelected.setTextColor(Color.GRAY)
                        item.setField(ShopCartItemFields.IS_SELECTED, false)
                    } else {
                        iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main))
                        item.setField(ShopCartItemFields.IS_SELECTED, true)
                    }
                }
                //商品减事件
                iconMinus.setOnClickListener {
                    val currentCount = item.getField(ShopCartItemFields.COUNT)!! as Int
                    if (Integer.parseInt(tvCount.text.toString()) > 1) {
                        RestClient.builder()
                                .url("http://mock.fulingjie.com/mock-android/data/add_shop_cart.json")
                                .loader(mContext)
                                .params("count", currentCount.toString()!!)
                                .success {
                                    var countNum = Integer.parseInt(tvCount.text.toString())
                                    countNum--
                                    tvCount.text = countNum.toString()
                                    if (mCartItemListener != null) {
                                        totalPrice = totalPrice - price!!
                                        val itemTotal = countNum * price!!
                                        mCartItemListener!!.onItemClick(itemTotal)
                                    }
                                }
                                .build()
                                .post()
                    } else {
                        //                            Toast.makeText()
                        ToastUtils.showShort("最少购买一件商品哦！")
                        ToastUtils.setGravity(Gravity.CENTER, 0, 0)
                    }
                }

                //商品加加事件
                iconPlus.setOnClickListener {
                    val currentCount = item.getField(ShopCartItemFields.COUNT)!!.toString()
                    RestClient.builder()
                            .url("http://mock.fulingjie.com/mock-android/data/add_shop_cart.json")
                            .loader(mContext)
                            .params("count", currentCount!!)
                            .success {
                                var countNum = Integer.parseInt(tvCount.text.toString())
                                countNum++
                                tvCount.text = countNum.toString()
                                if (mCartItemListener != null) {
                                    totalPrice = totalPrice + price!!
                                    val itemTotal = countNum * price!!
                                    mCartItemListener!!.onItemClick(itemTotal)
                                }
                            }
                            .build()
                            .post()
                }
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
