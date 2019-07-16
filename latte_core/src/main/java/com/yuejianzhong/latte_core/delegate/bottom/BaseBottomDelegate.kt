package com.yuejianzhong.latte_core.delegate.bottom

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import butterknife.BindView
import com.joanzapata.iconify.widget.IconTextView
import com.yuejianzhong.latte_core.R
import com.yuejianzhong.latte_core.R2
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import java.util.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment


abstract class BaseBottomDelegate : LatteDelegate(),View.OnClickListener {
    private val ITEM_DELEGATES = ArrayList<BottomItemDelegate>()
    private val TAB_BEANS = ArrayList<BottomTabBean>()

    private val ITEMS = LinkedHashMap<BottomTabBean, BottomItemDelegate>()
    private var mCurrentDelegate: Int = 0
    private var mIndexDelegate: Int = 0
    private var mClickedColor = Color.RED

    private lateinit var mBottomBar:LinearLayoutCompat

    public abstract fun setItems(builder: ItemBuilder): LinkedHashMap<BottomTabBean, BottomItemDelegate>

    public abstract fun setIndexDelegate(): Int

    public abstract fun setClickedColor(): Int

    override fun setLayout(): Any {
        return R.layout.delegate_bottom
    }

    //    @TargetApi(Build.VERSION_CODES.N)
//    @TargetApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIndexDelegate = setIndexDelegate()
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor()
        }

        val builder = ItemBuilder.builder()
        var items: LinkedHashMap<BottomTabBean, BottomItemDelegate> = setItems(builder)
        ITEMS.putAll(items)
        //        items.
        //        items.
//        items.forEach { key, value ->
//            TAB_BEANS.add(key)
//            ITEM_DELEGATES.add(value)
//        }

        for (item in ITEMS.entries) {
            TAB_BEANS.add(item.key)
            ITEM_DELEGATES.add(item.value)
        }

    }


    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {

        mBottomBar = rootView!!.findViewById(R.id.bottom_bar)
        val size = ITEMS.size
        for (i in 1..size) {
            LayoutInflater.from(context).inflate(R.layout.bottom_item_icon_text_layout,mBottomBar)
            var item: RelativeLayout = mBottomBar.getChildAt(i-1) as RelativeLayout
            //设置点击事件
            item.tag = i - 1
            item.setOnClickListener(this)
            val itemIcon: IconTextView = item.getChildAt(0) as IconTextView
            val itemTitle: AppCompatTextView = item.getChildAt(1) as AppCompatTextView
            val bean: BottomTabBean = TAB_BEANS[i - 1]
            //初始化数据
            itemIcon.text = bean.icon
            itemTitle.text = bean.title
            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor)
                itemTitle.setTextColor(mClickedColor)
            }

        }

        val delegate = ITEM_DELEGATES.toArray()
        val delegateArray = ITEM_DELEGATES.toArray(arrayOfNulls<ISupportFragment>(size))
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, *delegateArray)
    }

    override fun onClick(v: View?) {
        Log.d("BaseBottomDelegate","点击")
        val tabIndex = v!!.tag as Int
        resetColor(tabIndex)
        supportDelegate.showHideFragment(ITEM_DELEGATES[tabIndex], ITEM_DELEGATES[mCurrentDelegate])
        //注意先后顺序
        mCurrentDelegate = tabIndex
    }

    private fun resetColor(index:Int){
        val count: Int = mBottomBar.childCount-1
        for (i in 0..count) {
            if (i != index) {
                val item: RelativeLayout = mBottomBar.getChildAt(i) as RelativeLayout
                val itemIcon = item.getChildAt(0) as IconTextView
                itemIcon.setTextColor(Color.GRAY)
                val itemTitle = item.getChildAt(1) as AppCompatTextView
                itemTitle.setTextColor(Color.GRAY)
            }else{
                val item: RelativeLayout = mBottomBar.getChildAt(i) as RelativeLayout
                val itemIcon = item.getChildAt(0) as IconTextView
                itemIcon.setTextColor(mClickedColor)
                val itemTitle = item.getChildAt(1) as AppCompatTextView
                itemTitle.setTextColor(mClickedColor)
            }
        }

    }

}