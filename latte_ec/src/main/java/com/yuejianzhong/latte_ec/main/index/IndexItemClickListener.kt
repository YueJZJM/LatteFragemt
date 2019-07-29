package com.yuejianzhong.latte_ec.main.index

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.SimpleClickListener
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.detail.GoodsDelegate

class IndexItemClickListener private constructor(delegate: LatteDelegate) : SimpleClickListener() {

    private val DELEGATE:LatteDelegate

    init {
        DELEGATE = delegate
    }

    companion object{
        fun create(delegate:LatteDelegate):IndexItemClickListener{
            return IndexItemClickListener(delegate)
        }
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }

    override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }

    override fun onItemChildLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        val goodsDelegate:GoodsDelegate = GoodsDelegate.create()
        DELEGATE.start(goodsDelegate)
    }

}