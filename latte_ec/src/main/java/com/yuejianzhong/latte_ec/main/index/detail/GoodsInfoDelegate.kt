package com.yuejianzhong.latte_ec.main.index.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R
import android.support.v7.widget.AppCompatTextView
import kotlinx.android.synthetic.main.delegate_goods_info.*


class GoodsInfoDelegate : LatteDelegate() {

    var mData: JSONObject? = null

    companion object{
        val ARG_GOODS_ID = "ARG_GOODS_ID"
        @JvmStatic
        fun create(goods:String):GoodsInfoDelegate{
            val args = Bundle()
            args.putString(ARG_GOODS_ID, goods)
            val goodsInfoDelegate = GoodsInfoDelegate()
            goodsInfoDelegate.arguments = args
            return goodsInfoDelegate
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        val goodsData = args?.getString(ARG_GOODS_ID)
        mData = JSON.parseObject(goodsData)
    }

    override fun setLayout(): Any {
        return R.layout.delegate_goods_info
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = mData?.getString("name")
        val desc = mData?.getString("description")
        val price = mData?.getDouble("price")
        tv_goods_info_title.text = name
        tv_goods_info_desc.text = desc
        tv_goods_info_price.text = price.toString()
    }

}