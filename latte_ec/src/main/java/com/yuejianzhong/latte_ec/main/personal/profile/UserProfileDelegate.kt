package com.yuejianzhong.latte_ec.main.personal.profile

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R
import android.support.v7.widget.LinearLayoutManager
import com.yuejianzhong.latte_ec.main.personal.list.ListAdapter
import com.yuejianzhong.latte_ec.main.personal.list.ListBean
import com.yuejianzhong.latte_ec.main.personal.list.ListItemType
import com.yuejianzhong.latte_ec.main.personal.settings.NameDelegate
import kotlinx.android.synthetic.main.delegate_user_profile.*


class UserProfileDelegate : LatteDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_user_profile
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build()

        val name = ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setFragment(NameDelegate())
                .setValue("未设置姓名")
                .build()

        val gender = ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性别")
                .setValue("未设置性别")
                .build()

        val birth = ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未设置生日")
                .build()

        val data = ArrayList<ListBean>()
        data.add(image)
        data.add(name)
        data.add(gender)
        data.add(birth)

        //设置RecyclerView
        val manager = LinearLayoutManager(context)
        rv_user_profile.layoutManager = manager
        val adapter = ListAdapter(data)
        rv_user_profile.adapter = adapter
        rv_user_profile.addOnItemTouchListener(UserProfileClickListener(this))
    }
}