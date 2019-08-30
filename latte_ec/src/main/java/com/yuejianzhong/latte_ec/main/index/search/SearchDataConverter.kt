package com.yuejianzhong.latte_ec.main.index.search

import com.alibaba.fastjson.JSONArray
import com.blankj.utilcode.util.LogUtils
import com.yuejianzhong.latte_core.ui.recycler.DataConverter
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.yuejianzhong.latte_core.util.storage.LattePreference



class SearchDataConverter : DataConverter() {

    companion object{
        val TAG_SEARCH_HISTORY = "search_history"
    }


    override fun conver(): ArrayList<MultipleItemEntity> {
        val jsonStl = LattePreference.getCustomAppProfile(TAG_SEARCH_HISTORY)
        if (jsonStl != "") {
            val array = JSONArray.parseArray(jsonStl)
            val size = array.size
            for (i in 0 until size) {
                val historyItemText = array.getString(i)
                val entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build()
                ENTITLES.add(entity)
            }
        }
        return ENTITLES
    }

}