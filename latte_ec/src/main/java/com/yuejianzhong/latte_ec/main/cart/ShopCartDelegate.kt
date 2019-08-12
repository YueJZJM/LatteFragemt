package com.yuejianzhong.latte_ec.main.cart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_ec.R
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import kotlinx.android.synthetic.main.delegate_shop_cart.*


class ShopCartDelegate : BottomItemDelegate(),ISuccess, ICartItemListener {
    override fun onItemClick(itemTotalPrice: Double) {
    }

    private var mAdapter: ShopCartAdapter? = null
    //购物车数量标记
    private val mCurrentCount = 0
    private val mTotalCount = 0
    private val mTotalPrice = 0.00

    override fun setLayout(): Any {
        return R.layout.delegate_shop_cart
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/shop_cart_data.json")
                .loader(context)
                .success {
                    val data = ShopCartDataConverter()
                            .setJsonData(it)
                            .conver()
                    Log.d("aaaaa","aaa")
                    mAdapter = ShopCartAdapter(data)
                    mAdapter!!.setCartItemListener(this)
                    val manager = LinearLayoutManager(context)
                    rv_shop_cart.layoutManager = manager
                    rv_shop_cart.adapter = mAdapter
                }
                .build()
                .get()
    }


    override fun onSuccess(response: String) {
//        val data = ShopCartDataConverter()
//                .setJsonData(response)
//                .conver()
//        Log.d("aaaaa","aaa")
//        mAdapter = ShopCartAdapter(data)
//        mAdapter!!.setCartItemListener(this)
//        val manager = LinearLayoutManager(context)
//        rv_shop_cart.layoutManager = manager
//        rv_shop_cart.adapter = mAdapter
//        mTotalPrice = mAdapter.getTotalPrice()
//        mTvTotalPrice.setText(String.valueOf(mTotalPrice))
//        checkItemCount()
    }

}