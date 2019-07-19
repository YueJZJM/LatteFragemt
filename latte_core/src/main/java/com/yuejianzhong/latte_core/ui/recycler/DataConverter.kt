package com.yuejianzhong.latte_core.ui.recycler

abstract class DataConverter{
    open val ENTITLES = ArrayList<MultipleItemEntity>()

    open var mJsonData: String? = null

    public abstract fun conver(): ArrayList<MultipleItemEntity>
}
