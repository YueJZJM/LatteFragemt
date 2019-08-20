package com.yuejianzhong.latte_ec.main.personal.address

import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.alibaba.fastjson.JSON
import com.yuejianzhong.latte_core.ui.recycler.DataConverter


class AddressDataConverter : DataConverter() {
    override fun conver(): ArrayList<MultipleItemEntity> {

        val array = JSON.parseObject(mJsonData).getJSONArray("data")
        val size = array.size
        for (i in 0 until size) {

            val data = array.getJSONObject(i)
            val id = data.getInteger("id")
            val name = data.getString("name")
            val phone = data.getString("phone")
            val address = data.getString("address")
            val isDefault = data.getBoolean("default")

            val entity = MultipleItemEntity.builder()
                    .setItemType(AddressItemType.ITEM_ADDRESS)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.NAME, name)
                    .setField(MultipleFields.TAG, isDefault)
                    .setField(AddressItemFields.ADDRESS, address)
                    .setField(AddressItemFields.PHONE, phone)
                    .build()
            ENTITLES.add(entity)

        }

        return ENTITLES
    }
}
