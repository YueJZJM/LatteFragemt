package com.yuejianzhong.latte_ec.main.index

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.yuejianzhong.latte_core.ui.recycler.DataConverter
import com.yuejianzhong.latte_core.ui.recycler.ItemType
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity

class IndexDataConverter : DataConverter() {

    override fun conver(): ArrayList<MultipleItemEntity> {
        val dataArray : JSONArray = JSON.parseObject(mJsonData).getJSONArray("data")
        val size = dataArray.size
        for (i in 0 until size-1) {
            val data:JSONObject = dataArray.getJSONObject(i)
            val imageUlr: String? = data.getString("imageUrl")
            val text:String? = data.getString("text")
            val spanSize = data.getInteger("spanSize")
            val id = data.getInteger("goodsId")
            val banners: JSONArray? = data.getJSONArray("banners")

            val bannerImages = ArrayList<String>()
            var type = 0
            if (imageUlr == null && text != null) {
                type = ItemType.TEXT
            }else if (imageUlr != null && text == null) {
                type = ItemType.IMAGE
            }else if (imageUlr != null) {
                type = ItemType.TEXT_IMAGE
            }else if (banners != null) {
                type = ItemType.BANNER
                //banner 的初始化
                val bannerSize:Int = banners.size
                for (j in 0 until bannerSize - 1) {
                    val banner = banners.getString(j)
                    bannerImages.add(banner)
                }
            }

            val entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text!!)
                    .setField(MultipleFields.IMAGE_URL, imageUlr!!)
                    .setField(MultipleFields.BANNERS,bannerImages)
                    .build()
            ENTITLES.add(entity)
        }
        return ENTITLES
    }

}

