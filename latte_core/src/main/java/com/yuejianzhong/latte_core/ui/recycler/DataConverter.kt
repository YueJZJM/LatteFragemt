package com.yuejianzhong.latte_core.ui.recycler


abstract class DataConverter{
    open val ENTITLES = ArrayList<MultipleItemEntity>()

    open lateinit var mJsonData: String

    public abstract fun conver(): ArrayList<MultipleItemEntity>


    fun setJsonData(json: String): DataConverter {
        this.mJsonData = json
        return this
    }

    fun clearData() {
        ENTITLES.clear()
    }
}
