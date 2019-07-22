package com.yuejianzhong.latte_core.ui.recycler

import java.util.*

class MultipleEntityBulider {
    companion object{
        private val FIELDS = LinkedHashMap<Any,Any>()
    }

    //先与主构造函数执行
    init {
        FIELDS.clear()
    }

    public final fun setType(itemType:Int):MultipleEntityBulider{
        FIELDS[MultipleFields.ITEM_TYPE] = itemType
        return this
    }

    public final fun setField(key: Any?, value: Any?): MultipleEntityBulider {
        if (key == null || value == null) {
            return this
        }
        FIELDS[key] = value
        return this
    }

    public final fun setFields(weakHashMap: LinkedHashMap<Any,Any>): MultipleEntityBulider {
        FIELDS.putAll(weakHashMap)
        return this
    }

    public final fun build():MultipleItemEntity{
        return MultipleItemEntity(FIELDS)
    }

}