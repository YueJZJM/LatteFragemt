package com.yuejianzhong.latte_core.ui.recycler

import android.support.annotation.ColorInt
import com.choices.divider.DividerItemDecoration

class BaseDecoration public constructor(@ColorInt var color:Int,var size:Int):DividerItemDecoration(){
  init {
      setDividerLookup(DividerLookupImp(color, size))
  }
    companion object{
        fun create(@ColorInt color:Int, size:Int):BaseDecoration{
            return BaseDecoration(color,size)
        }
    }
}