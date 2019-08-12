package com.yuejianzhong.latte_ec.main.cart

import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.alibaba.fastjson.JSON
import com.yuejianzhong.latte_core.ui.recycler.DataConverter


class ShopCartDataConverter : DataConverter() {
    override fun conver(): ArrayList<MultipleItemEntity> {

        val dataList = ArrayList<MultipleItemEntity>()
        val dataArray = JSON.parseObject(mJsonData).getJSONArray("data")
        val size = dataArray.size
        for (i in 0 until size) {
            val data = dataArray.getJSONObject(i)
            val title = data.getString("title")
            val desc = data.getString("desc")
            val id = data.getInteger("id")
            val count = data.getInteger("count")
            val price = data.getDouble("price")
            val thumb = data.getString("thumb")

            val entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.PRICE, price)
                    .setField(ShopCartItemFields.COUNT, count)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.IS_SELECTED, false)
                    .setField(ShopCartItemFields.POSITION, i)
                    .build()

            dataList.add(entity)

        }

        return dataList
    }
}
