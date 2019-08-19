package com.yuejianzhong.latte_ec.main.personal.settings

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R

class NameDelegate : LatteDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_name;
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

}