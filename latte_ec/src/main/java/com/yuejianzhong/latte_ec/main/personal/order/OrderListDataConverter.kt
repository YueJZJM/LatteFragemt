package com.yuejianzhong.latte_ec.main.personal.order

import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.alibaba.fastjson.JSON
import com.yuejianzhong.latte_core.ui.recycler.DataConverter


class OrderListDataConverter : DataConverter() {

    override fun conver(): ArrayList<MultipleItemEntity> {
        val array = JSON.parseObject(mJsonData).getJSONArray("data")
        val size = array.size
        for (i in 0 until size) {
            val data = array.getJSONObject(i)
            val thumb = data.getString("thumb")
            val title = data.getString("title")
            val id = data.getInteger("id")
            val price = data.getDouble("price")
            val time = data.getString("time")

            val entity = MultipleItemEntity.builder()
                    .setItemType(OrderListItemType.ITEM_ORDER_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(OrderItemFields.PRICE, price)
                    .setField(OrderItemFields.TIME, time)
                    .build()

            ENTITLES.add(entity)
        }
        return ENTITLES
    }
}
