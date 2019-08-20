package com.yuejianzhong.latte_ec.main.personal

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yuejianzhong.latte_ec.main.personal.list.ListBean
import com.chad.library.adapter.base.listener.SimpleClickListener
import com.yuejianzhong.latte_core.delegate.LatteDelegate


class PersonalClickListener(private val FRAGMENT: LatteDelegate) : SimpleClickListener() {

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val bean = baseQuickAdapter.data[position] as ListBean
        when (bean.id) {
            1 -> FRAGMENT.getParentDelegate<LatteDelegate>().supportDelegate.start(bean.fragment)
            2 -> FRAGMENT.getParentDelegate<LatteDelegate>().supportDelegate.start(bean.fragment)
            else -> {
            }
        }
    }

    override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }

    override fun onItemChildLongClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }
}
