package com.yuejianzhong.latte_ec.main

import android.graphics.Color
import com.yuejianzhong.latte_core.delegate.bottom.BaseBottomDelegate
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_core.delegate.bottom.BottomTabBean
import com.yuejianzhong.latte_core.delegate.bottom.ItemBuilder
import com.yuejianzhong.latte_ec.main.cart.ShopCartDelegate
import com.yuejianzhong.latte_ec.main.discover.DiscoverDelegate
import com.yuejianzhong.latte_ec.main.index.IndexDelegate
import com.yuejianzhong.latte_ec.main.sort.SortDelegate
import java.util.LinkedHashMap



class EcBottomDelegate : BaseBottomDelegate() {


    override fun setItems(builder: ItemBuilder): LinkedHashMap<BottomTabBean, BottomItemDelegate> {
        val items = LinkedHashMap<BottomTabBean, BottomItemDelegate>()
        items[BottomTabBean("{fa-home}", "主页")] = IndexDelegate()
        items[BottomTabBean("{fa-sort}", "分类")] = SortDelegate()
        items[BottomTabBean("{fa-compass}", "发现")] = DiscoverDelegate()
        items[BottomTabBean("{fa-shopping-cart}", "购物车")] = ShopCartDelegate()
        items[BottomTabBean("{fa-user}", "我的")] = IndexDelegate()
        return builder.addItems(items).build()
    }

    override fun setIndexDelegate(): Int {
        return 0
    }

    override fun setClickedColor(): Int {
        return Color.parseColor("#ffff8800");
    }

}