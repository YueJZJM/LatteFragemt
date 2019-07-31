package com.yuejianzhong.latte_ec.main.sort.list

import com.yuejianzhong.latte_core.ui.recycler.DataConverter
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.ItemType
import com.alibaba.fastjson.JSON



class VerticalListDataConverter : DataConverter() {
    override fun conver(): ArrayList<MultipleItemEntity> {
        val dataList = ArrayList<MultipleItemEntity>()
        val dataArray = JSON
                .parseObject(mJsonData)
                .getJSONObject("data")
                .getJSONArray("list")

        val size = dataArray.size
        for (i in 0 until size) {
            val data = dataArray.getJSONObject(i)
            val id = data.getInteger("id")
            val name = data.getString("name")

            val entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false)
                    .build()

            dataList.add(entity)
            //设置第一个被选中
            dataList[0].setField(MultipleFields.TAG, true)
        }

        return dataList
    }

}