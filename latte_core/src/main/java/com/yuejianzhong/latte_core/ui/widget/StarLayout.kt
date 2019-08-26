package com.yuejianzhong.latte_core.ui.widget

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import android.view.View
import com.joanzapata.iconify.widget.IconTextView
import android.view.ViewGroup
import android.view.Gravity
import com.yuejianzhong.latte_core.R


class StarLayout : LinearLayoutCompat, View.OnClickListener {


    companion object{
        private val ICON_UN_SELECT = "{fa-star-o}"
        private val ICON_SELECTED = "{fa-star}"
        private val STAR_TOTAL_COUNT = 5
        private val STARS = ArrayList<IconTextView>()

    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initStarIcon()
    }

    private fun initStarIcon() {
        for (i in 0 until STAR_TOTAL_COUNT) {
            val star = IconTextView(context)
            star.gravity = Gravity.CENTER
            val lp = LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            lp.weight = 1f
            star.layoutParams = lp
            star.text = ICON_UN_SELECT
            star.setTag(R.id.star_count, i)
            star.setTag(R.id.star_is_select, false)
            star.setOnClickListener(this)
            STARS.add(star)
            this.addView(star)
        }
    }

    private fun selectStar(count: Int) {
        for (i in 0..count) {
            val star = STARS[i]
            star.text = ICON_SELECTED
            star.setTextColor(Color.RED)
            star.setTag(R.id.star_is_select, true)
        }
    }

    private fun unSelectStar(count: Int) {
        for (i in 0 until STAR_TOTAL_COUNT) {
            if (i >= count) {
                val star = STARS[i]
                star.text = ICON_UN_SELECT
                star.setTextColor(Color.GRAY)
                star.setTag(R.id.star_is_select, false)
            }
        }
    }

    fun getStarCount(): Int {
        var count = 0
        for (i in 0 until STAR_TOTAL_COUNT) {
            val star = STARS[i]
            val isSelect = star.getTag(R.id.star_is_select) as Boolean
            if (isSelect) {
                count++
            }
        }
        return count
    }

    override fun onClick(v: View?) {
        val star = v as IconTextView
        //获取第几个星星
        val count = star.getTag(R.id.star_count) as Int
        //获取点击状态
        val isSelect = star.getTag(R.id.star_is_select) as Boolean
        if (!isSelect) {
            selectStar(count)
        } else {
            unSelectStar(count)
        }
    }

}