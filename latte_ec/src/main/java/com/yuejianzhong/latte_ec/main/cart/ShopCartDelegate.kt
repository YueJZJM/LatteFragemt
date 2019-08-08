package com.yuejianzhong.latte_ec.main.cart

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_ec.R

class ShopCartDelegate() : BottomItemDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_shop_cart
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

}