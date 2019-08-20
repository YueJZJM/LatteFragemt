package com.yuejianzhong.latte_ec.main.personal.address

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_ec.R
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import kotlinx.android.synthetic.main.delegate_address.*


class AddressDelegate : LatteDelegate() {


    override fun setLayout(): Any {
        return R.layout.delegate_address
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/address.json")
                .loader(context)
                .success {
                    LogUtils.d("AddressFragment", it)
                    val manager = LinearLayoutManager(context)
                    rv_address.layoutManager = manager
                    val data = AddressDataConverter().setJsonData(it).conver()
                    val adapter = AddressAdapter(data)
                    rv_address.adapter = adapter
                }
                .build()
                .get()
    }

}