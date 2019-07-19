package com.yuejianzhong.latte_core.ui.recycler

import com.chad.library.adapter.base.entity.MultiItemEntity
import java.lang.ref.ReferenceQueue
import java.lang.ref.SoftReference
import java.util.*
import kotlin.collections.LinkedHashMap

public class MultipleItemEntity: MultiItemEntity {

    private val ITEM_QUEUE = ReferenceQueue<LinkedHashMap<Any,Any>>()
    private val MULTIPLE_FIELDE = LinkedHashMap<Any,Any>()
    private val FIELDS_REFERENCE = SoftReference<LinkedHashMap<Any, Any>>(MULTIPLE_FIELDE,ITEM_QUEUE)

    constructor(fields: LinkedHashMap<Any, Any>){
        FIELDS_REFERENCE.get()?.putAll(fields)
    }

    companion object {
        public fun builder(): MultipleEntityBulider {
            return MultipleEntityBulider()
        }
    }

    override fun getItemType(): Int {
        return FIELDS_REFERENCE.get()?.get(MultipleFields.ITEM_TYPE) as Int
    }

    public fun  getField(key: Any): Any? {
        return FIELDS_REFERENCE.get()?.get(key)
    }

    fun getFields(): LinkedHashMap<Any, Any>? {
        return FIELDS_REFERENCE.get()
    }

    fun setField(key: Any, value: Any):MultiItemEntity {
        FIELDS_REFERENCE.get()?.set(key, value)
        return this
    }

}