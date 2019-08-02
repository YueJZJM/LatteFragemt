package com.yuejianzhong.latte_ec.main.sort.content

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yuejianzhong.latte_ec.R
import com.bumptech.glide.Glide
import android.support.v7.widget.AppCompatImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class SectionAdapter(layoutResId: Int, sectionHeadResId: Int, data: List<SectionBean>) : BaseSectionQuickAdapter<SectionBean, BaseViewHolder>(layoutResId, sectionHeadResId, data) {

    private val OPTIONS = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()


    override fun convertHead(helper: BaseViewHolder?, item: SectionBean?) {
        helper?.let {
            helper.setText(R.id.header, item?.header)
            helper.setVisible(R.id.more, item!!.isMore)
            helper.addOnClickListener(R.id.more)
        }
    }


    override fun convert(helper: BaseViewHolder, item: SectionBean) {
        //item.t返回SectionBean类型
        val thumb = item.t.goodsThumb
        val name = item.t.goodsName
        val goodsId = item.t.goodsId
        val entity = item.t
        helper.setText(R.id.tv, name)
        val goodsImageView = helper.getView<AppCompatImageView>(R.id.iv)
        Glide.with(mContext)
                .load(thumb)
                .into(goodsImageView)
    }

}