package com.yuejianzhong.latte_core.ui.recycler

import android.support.annotation.ColorRes
import com.choices.divider.Divider
import com.choices.divider.DividerItemDecoration

class DividerLookupImp constructor(val color:Int,val size:Int) :DividerItemDecoration.DividerLookup{


    override fun getHorizontalDivider(position: Int): Divider {
        return Divider.Builder().size(size)
                .color(color)
                .build()
    }

    override fun getVerticalDivider(position: Int): Divider {
        return Divider.Builder().size(size)
                .color(color)
                .build()
    }

}