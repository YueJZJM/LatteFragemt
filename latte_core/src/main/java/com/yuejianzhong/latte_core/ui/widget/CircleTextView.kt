package com.yuejianzhong.latte_core.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import kotlin.math.max
import android.support.annotation.ColorInt




class CircleTextView : AppCompatTextView {

    private val PAINT: Paint = Paint()
    private val FILTER: PaintFlagsDrawFilter = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)

    constructor(context:Context):this(context,null)
    constructor(context:Context, attrs: AttributeSet?):super(context,attrs){
        PAINT.color = Color.WHITE
        PAINT.isAntiAlias = true

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = maxHeight
        val max = max(width, height)
        setMeasuredDimension(max, max)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawFilter = FILTER
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(),
                (max(width, height) / 2).toFloat(), PAINT)
        super.draw(canvas)
    }

    fun setCircleBackground(@ColorInt color: Int) {
        PAINT.color = color
    }
}