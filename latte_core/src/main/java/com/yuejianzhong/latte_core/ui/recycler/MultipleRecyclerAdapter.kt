package com.yuejianzhong.latte_core.ui.recycler

import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.listener.OnItemClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yuejianzhong.latte_core.R
import com.yuejianzhong.latte_core.ui.banner.BannerCreator

open class MultipleRecyclerAdapter constructor(data: List<MultipleItemEntity>) : BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>(data)
        , BaseQuickAdapter.SpanSizeLookup,OnItemClickListener {

    init {
        initItem()
    }

    override fun onItemClick(position: Int) {

    }

    //确保初始一次 banner
    private var mIsInitBanner = false

    companion object {
        fun create(data: List<MultipleItemEntity>): MultipleRecyclerAdapter {
            return MultipleRecyclerAdapter(data)
        }

        fun create(converter: DataConverter): MultipleRecyclerAdapter {
            return MultipleRecyclerAdapter(converter.conver())
        }
    }


    override fun getSpanSize(gridLayoutManager: GridLayoutManager?, position: Int): Int {
        return data[position].getField(MultipleFields.SPAN_SIZE) as Int
    }


    /**
     * 设置不同的布局
     */
    private fun initItem(){
        addItemType(ItemType.TEXT,R.layout.item_multiple_text)
        addItemType(ItemType.IMAGE,R.layout.item_multiple_image)
        addItemType(ItemType.TEXT_IMAGE,R.layout.item_multiple_image_text)
        addItemType(ItemType.BANNER,R.layout.item_multiple_banner)
        openLoadAnimation()
        //设置宽度监听
        setSpanSizeLookup(this)
        //多次执行动画
        isFirstOnly(false)
    }

    /**
     * 传入 viewholder
     */
    override fun createBaseViewHolder(view: View?): MultipleViewHolder {
        return MultipleViewHolder.create(view)
    }


    /**
     * 不同的数据与不同的视图在此方法中绑定
     */
    override fun convert(holder: MultipleViewHolder?, entity: MultipleItemEntity?) {
        val text: String
        val imageUrl:String
        val bannerImages: ArrayList<String>
        if (holder != null) {
            when (holder.itemViewType) {
                ItemType.TEXT -> {
                    text = entity?.getField(MultipleFields.TEXT) as String
                    holder.setText(R.id.text_single, text)
                }
                ItemType.IMAGE -> {
                    imageUrl = entity?.getField(MultipleFields.IMAGE_URL) as String
                    Glide.with(mContext).load(imageUrl)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .centerCrop()
                            .into(holder.getView(R.id.img_single))
                }
                ItemType.TEXT_IMAGE -> {
                    text = entity?.getField(MultipleFields.TEXT) as String
                    imageUrl = entity.getField(MultipleFields.IMAGE_URL) as String
                    Glide.with(mContext).load(imageUrl)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .centerCrop()
                            .into(holder.getView(R.id.img_multiple))
                    holder.setText(R.id.tv_multiple,text)
                }
                ItemType.BANNER ->{
                    if (!mIsInitBanner) {
                        bannerImages = entity?.getField(MultipleFields.BANNERS) as ArrayList<String>
                        val convenientBanner:ConvenientBanner<String> = holder.getView(R.id.banner_recycler_item)
                        BannerCreator.setDefault(convenientBanner,bannerImages,this)
                    }
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}