package com.yuejianzhong.latte_ec.main.index

import android.os.Bundle
import android.util.Log
import android.view.View
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_core.ui.refresh.RefreshHandler
import com.yuejianzhong.latte_ec.R
import kotlinx.android.synthetic.main.delegate_index.*

class IndexDelegate : BottomItemDelegate() {
//    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
//    }

    private lateinit var mRefreshHandler: RefreshHandler


    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initRefreshLayout()
    }

    override fun setLayout(): Any {
        return R.layout.delegate_index
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
//        mRefreshHandler= RefreshHandler(srl_index)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRefreshHandler= RefreshHandler(srl_index)
    }

    private fun initRefreshLayout(){
        srl_index.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_red_light
        )
        srl_index.setProgressViewOffset(true,120,300)
        Log.d("initRefreshLayout",srl_index.toString())
    }
}