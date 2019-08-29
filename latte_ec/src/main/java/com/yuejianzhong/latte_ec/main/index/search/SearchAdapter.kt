package com.yuejianzhong.latte_ec.main.index.search

import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import android.support.v7.widget.AppCompatTextView
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.yuejianzhong.latte_core.ui.recycler.MultipleViewHolder
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.yuejianzhong.latte_ec.R


class SearchAdapter(data: List<MultipleItemEntity>) : BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>(data) {

    init {
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search)
    }

    override fun convert(helper: MultipleViewHolder, item: MultipleItemEntity) {
        when (helper.itemViewType) {
            SearchItemType.ITEM_SEARCH -> {
                val tvSearchItem = helper.getView<AppCompatTextView>(R.id.tv_search_item)
                val history:String = item.getField(MultipleFields.TEXT) as String
                tvSearchItem.text = history
            }
            else -> {
            }
        }
    }
}