package com.yuejianzhong.latte_ec.main.personal.order

import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R
import com.yuejianzhong.latte_ec.main.personal.PersonalDelegate
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import android.support.v7.widget.LinearLayoutManager
import com.yuejianzhong.latte_core.log.LogUtils
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_core.net.RestClient
import kotlinx.android.synthetic.main.delegate_order_list.*


class OrderListDelegate : LatteDelegate() {

    private var mType:String?= null

    override fun setLayout(): Any {
        return R.layout.delegate_order_list

    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        mType = args?.getString(PersonalDelegate.ORDER_TYPE)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RestClient.builder()
                //                .loader(getContext())
                .url("http://mock.fulingjie.com/mock-android/data/order_list.json")
                .params("type", mType)
                .success { response ->
                    LogUtils.d(response)
                    val manager = LinearLayoutManager(context)
                    rv_order_list.layoutManager = manager
                    val data = OrderListDataConverter().setJsonData(response).conver()
                    val adapter = OrderListAdapter(data)
                    rv_order_list.adapter = adapter
                    rv_order_list.addOnItemTouchListener(OrderListClickListener(this@OrderListDelegate))
                }
                .build()
                .get()
    }


    override fun onLazyInitView(@Nullable savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)

    }
}