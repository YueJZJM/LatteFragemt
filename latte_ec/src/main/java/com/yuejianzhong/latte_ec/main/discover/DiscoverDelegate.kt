package com.yuejianzhong.latte_ec.main.discover

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_ec.R

class DiscoverDelegate : BottomItemDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_discover
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

}