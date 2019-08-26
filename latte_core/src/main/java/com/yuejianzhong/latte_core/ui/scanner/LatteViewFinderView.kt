package com.yuejianzhong.latte_core.ui.scanner

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import me.dm7.barcodescanner.core.ViewFinderView

class LatteViewFinderView : ViewFinderView {
    constructor(context: Context?):this(context,null)

    constructor(context: Context?, attributeSet: AttributeSet?):super(context, attributeSet){
        mSquareViewFinder = true
        mBorderPaint.color = Color.GREEN;
        mLaserPaint.color = Color.GREEN;
    }
}