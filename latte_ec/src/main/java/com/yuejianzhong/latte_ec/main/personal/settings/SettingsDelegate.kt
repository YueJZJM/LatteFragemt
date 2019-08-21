package com.yuejianzhong.latte_ec.main.personal.settings

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R
import android.support.v7.widget.LinearLayoutManager
import com.yuejianzhong.latte_ec.main.personal.list.ListBean
import com.yuejianzhong.latte_ec.main.personal.list.ListItemType
import com.blankj.utilcode.util.ToastUtils
import com.yuejianzhong.latte_core.util.callback.CallbackType
import com.yuejianzhong.latte_core.util.callback.CallbackManager
import android.widget.CompoundButton
import com.blankj.utilcode.util.BarUtils.getStatusBarHeight
import android.support.v7.widget.RecyclerView
import com.yuejianzhong.latte_ec.main.personal.address.AddressDelegate
import com.yuejianzhong.latte_ec.main.personal.list.ListAdapter
import kotlinx.android.synthetic.main.delegate_settings.*


class SettingsDelegate : LatteDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_settings

    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        val mRecyclerView = `$`<RecyclerView>(R.id.rv_settings)

        tb_settings.setPadding(0, getStatusBarHeight(), 0, 0)
        val push = ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setFragment(AddressDelegate())
                .setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback("start")
                        ToastUtils.showShort("推送已打开")
                    } else {
                        CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback("stop")
                        ToastUtils.showShort("推送已关闭")
                    }
                })
                .setText("消息推送")
                .build()

        val about = ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setFragment(AboutDelegate())
                .setText("关于我们")
                .build()

        val data = ArrayList<ListBean>()
        data.add(push)
        data.add(about)

        //设置RecyclerView
        val manager = LinearLayoutManager(context)
        rv_settings.layoutManager = manager
        val adapter = ListAdapter(data)
        rv_settings.adapter = adapter
        rv_settings.addOnItemTouchListener(SettingsClickListener(this))
    }

}