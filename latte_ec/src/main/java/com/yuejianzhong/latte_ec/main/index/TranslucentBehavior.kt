package com.yuejianzhong.latte_ec.main.index

import android.content.Context
import android.support.annotation.NonNull
import com.yuejianzhong.latte_core.app.Latte
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import com.yuejianzhong.latte_ec.R


class TranslucentBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<Toolbar>(context, attrs) {

    //注意这个变量一定要定义成类变量
    private var mOffset = 0

    override fun onStartNestedScroll(
            @NonNull coordinatorLayout: CoordinatorLayout, @NonNull child: Toolbar, @NonNull directTargetChild: View, @NonNull target: View, axes: Int, type: Int): Boolean {
        return true
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: Toolbar, dependency: View): Boolean {
        return dependency.getId() === R.id.rv_index
    }


    override fun onNestedScroll(
            @NonNull coordinatorLayout: CoordinatorLayout, @NonNull toolbar: Toolbar, @NonNull target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        val startOffset = 0
        val context = Latte.getApplicationContext()
        val endOffset = context.resources.getDimensionPixelOffset(R.dimen.header_height) + MORE
        mOffset += dyConsumed
        if (mOffset <= startOffset) {
            toolbar.getBackground().setAlpha(0)
        } else if (mOffset > startOffset && mOffset < endOffset) {
            val percent = (mOffset - startOffset).toFloat() / endOffset
            val alpha = Math.round(percent * 255)
            toolbar.getBackground().setAlpha(alpha)
        } else if (mOffset >= endOffset) {
            toolbar.getBackground().setAlpha(255)
        }
    }

    companion object {
        //延长滑动过程
        private val MORE = 100
    }
}
