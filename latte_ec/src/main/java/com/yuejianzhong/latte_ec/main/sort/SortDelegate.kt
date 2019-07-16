package com.yuejianzhong.latte_ec.main.sort

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_ec.R

class SortDelegate : BottomItemDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_sort
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View?) {
    }


}