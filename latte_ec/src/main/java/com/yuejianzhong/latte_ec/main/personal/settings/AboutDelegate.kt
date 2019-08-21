package com.yuejianzhong.latte_ec.main.personal.settings

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R
import com.alibaba.fastjson.JSON
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_core.net.RestClient
import com.blankj.utilcode.util.BarUtils.getStatusBarHeight
import android.support.v7.widget.AppCompatTextView
import kotlinx.android.synthetic.main.delegate_about.*


class AboutDelegate : LatteDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_about
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val textView = `$`<AppCompatTextView>(R.id.tv_info)
//        val mToolbar = `$`<View>(R.id.tb_about)
        tb_about.setPadding(0, getStatusBarHeight(), 0, 0)
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/about.json")
                .success { response ->
                    val info = JSON.parseObject(response).getString("data")
                    tv_info.text = info
                }
                .build()
                .get()
    }

}