package com.yuejianzhong.latte_ec.detail

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R

class GoodsDelegate : LatteDelegate() {

    companion object{
        fun create(): GoodsDelegate {
            return GoodsDelegate()
        }
    }

    override fun setLayout(): Any {
        return R.layout.delagate_goods_detail

    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

}