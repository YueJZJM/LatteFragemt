package com.yuejianzhong.latte_core.delegate.bottom

import java.util.*

public final class ItemBuilder {
    private val ITEMS = LinkedHashMap<BottomTabBean, BottomItemDelegate>()

    companion object{
        final  fun  builder(): ItemBuilder {
            return ItemBuilder()
        }
    }


    final fun addItem(bean: BottomTabBean, delegate: BottomItemDelegate):ItemBuilder {
        ITEMS.put(bean, delegate)
        return this
    }

    public final fun addItems(items: LinkedHashMap<BottomTabBean, BottomItemDelegate>): ItemBuilder {
        ITEMS.putAll(items)
        return this
    }

    public final fun build(): LinkedHashMap<BottomTabBean, BottomItemDelegate> {
        return ITEMS
    }


}