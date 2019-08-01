package com.yuejianzhong.latte_ec.main.sort.content

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject

class SectionDataConverter {


    fun conver(json: String): List<SectionBean> {

        val dataList = ArrayList<SectionBean>()
        val dataArray:JSONArray = JSON.parseObject(json).getJSONArray("data")

        val size = dataArray.size

        for (i in 0 until size) {
            val data = dataArray.getJSONObject(i)
            val id = data.getInteger("id")
            val title = data.getString("section")

            //添加 title
            val sectionTitleBean = SectionBean(true, title)
            sectionTitleBean.id = id
            sectionTitleBean.isMore = true
            dataList.add(sectionTitleBean)

            //商品内容循环
            val goods = data.getJSONArray("goods")

            val goodSize = goods.size

            for (j in 0 until goodSize) {
                val contentItem = goods.getJSONObject(j)
                val goodsId = contentItem.getInteger("goods_id")!!
                val goodsName = contentItem.getString("goods_name")
                val goodsThumb = contentItem.getString("goods_thumb")
                //获取内容
                val itemEntity = SectionContentItemEntity()
                itemEntity.goodsId = goodsId
                itemEntity.goodsName = goodsName
                itemEntity.goodsThumb = goodsThumb
                //添加内容
                dataList.add(SectionBean(itemEntity))


            }

        }
        return dataList
    }
}