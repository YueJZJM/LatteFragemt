package com.yuejianzhong.latte_ec.main.sort

import android.os.Bundle
import android.util.Log
import android.view.View
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_ec.R
import com.yuejianzhong.latte_ec.main.sort.content.ContentDelegate
import com.yuejianzhong.latte_ec.main.sort.list.VerticalListDelegate

class SortDelegate : BottomItemDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_sort
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        val verticalListDelegate = VerticalListDelegate()
        loadRootFragment(R.id.vertical_list_container, verticalListDelegate)
        //默认显示第一个分类
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1))
    }

}