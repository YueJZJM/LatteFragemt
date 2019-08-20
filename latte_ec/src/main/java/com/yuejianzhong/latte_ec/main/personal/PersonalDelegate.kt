package com.yuejianzhong.latte_ec.main.personal

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_ec.R
import com.yuejianzhong.latte_ec.main.EcBottomDelegate
import android.support.v7.widget.LinearLayoutManager
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.delegate.bottom.BaseBottomDelegate
import com.yuejianzhong.latte_ec.R.id.rv_personal_setting
import com.yuejianzhong.latte_ec.main.personal.address.AddressDelegate
import com.yuejianzhong.latte_ec.main.personal.list.*
import com.yuejianzhong.latte_ec.main.personal.order.OrderListDelegate
import com.yuejianzhong.latte_ec.main.personal.profile.UserProfileDelegate
import kotlinx.android.synthetic.main.delegate_personal.*


class PersonalDelegate : BottomItemDelegate(),View.OnClickListener {

    companion object {
        val ORDER_TYPE: String = "ORDER_TYPE"
    }

    private var mArgs: Bundle? = null

    override fun setLayout(): Any {
        return R.layout.delegate_personal
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    private fun startOrderListByType() {
        val delegate = OrderListDelegate()
        delegate.arguments = mArgs
        getParentDelegate<BaseBottomDelegate>().supportDelegate.start(delegate)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mArgs = Bundle()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val address = ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setFragment(AddressDelegate())
                .setText("收货地址")
                .build()

        val system = ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
//                .setFragment(SettingsFragment())
                .setText("系统设置")
                .build()

        val data = ArrayList<ListBean>()
        data.add(address)
        data.add(system)

        val manager = LinearLayoutManager(context)
        rv_personal_setting.layoutManager = manager
        val adapter = ListAdapter(data)
        rv_personal_setting.adapter = adapter
        rv_personal_setting.addOnItemTouchListener(PersonalClickListener(this))

        tv_all_order.setOnClickListener {
            mArgs?.putString(ORDER_TYPE,"all")
            startOrderListByType()
        }
        tv_all_order.setOnClickListener(this@PersonalDelegate)
        img_user_avatar.setOnClickListener(this@PersonalDelegate)

    }

    override fun onClick(v: View?) {
        when (v) {
            tv_all_order -> {
                mArgs?.putString(ORDER_TYPE, "all")
                startOrderListByType()
            }
            img_user_avatar -> {
                getParentDelegate<BaseBottomDelegate>().supportDelegate.start(UserProfileDelegate())
            }
        }

    }


}