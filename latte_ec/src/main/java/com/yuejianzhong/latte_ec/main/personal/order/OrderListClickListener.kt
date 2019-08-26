package com.yuejianzhong.latte_ec.main.personal.order

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.SimpleClickListener

class OrderListClickListener(private val fragment: OrderListDelegate) : SimpleClickListener() {


    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        fragment.supportDelegate.start(OrderCommentDelegate())
    }

    override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    override fun onItemChildLongClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }
}
