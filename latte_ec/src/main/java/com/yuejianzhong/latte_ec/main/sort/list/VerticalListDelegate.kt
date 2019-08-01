package com.yuejianzhong.latte_ec.main.sort.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_ec.R
import kotlinx.android.synthetic.main.delegate_list_content.*
import kotlinx.android.synthetic.main.delegate_vertical_list.*
import android.R.attr.data
import android.util.Log
import com.yuejianzhong.latte_ec.main.sort.SortDelegate



class VerticalListDelegate : LatteDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_vertical_list
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()


    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/sort_list_data.json")
                .loader(context)
                .success(ISuccess {it->
                    val data = VerticalListDataConverter().setJsonData(it).conver()
                    val delegate = getParentDelegate<SortDelegate>()
                    val adapter = SortRecyclerAdapter(data, delegate)
                    rv_vertical_menu_list.adapter = adapter

                })
                .build()
                .get()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(context)
        rv_vertical_menu_list.layoutManager = manager
        //屏蔽动画效果
        rv_vertical_menu_list.itemAnimator = null

    }

}