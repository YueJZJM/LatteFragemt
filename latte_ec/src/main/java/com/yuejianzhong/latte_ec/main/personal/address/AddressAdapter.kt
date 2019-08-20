package com.yuejianzhong.latte_ec.main.personal.address

import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_core.net.RestClient
import com.blankj.utilcode.util.SnackbarUtils.getView
import android.support.v7.widget.AppCompatTextView
import android.view.View
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.yuejianzhong.latte_core.ui.recycler.MultipleViewHolder
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.yuejianzhong.latte_ec.R


class AddressAdapter(data: List<MultipleItemEntity>) : BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>(data) {

    init {
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address)
    }

    override fun convert(helper: MultipleViewHolder, item: MultipleItemEntity) {
        when (helper.itemViewType) {
            AddressItemType.ITEM_ADDRESS -> {
                val name = item.getField(MultipleFields.NAME) as String
                val phone = item.getField(AddressItemFields.PHONE) as String
                val address = item.getField(AddressItemFields.ADDRESS) as String
                val isDefault = item.getField(MultipleFields.TAG)!!
                val id = item.getField(MultipleFields.ID)!!.toString()

                val nameText = helper.getView<AppCompatTextView>(R.id.tv_address_name)
                val phoneText = helper.getView<AppCompatTextView>(R.id.tv_address_phone)
                val addressText = helper.getView<AppCompatTextView>(R.id.tv_address_address)
                val deleteTextView = helper.getView<AppCompatTextView>(R.id.tv_address_delete)
                deleteTextView.setOnClickListener {
                    RestClient.builder()
                            .url("http://mock.fulingjie.com/mock-android/data/address.json")
                            .params("id", id)
                            .success { remove(helper.layoutPosition) }
                            .build()
                            .post()
                }

                nameText.text = name
                phoneText.text = phone
                addressText.text = address
            }
            else -> {
            }
        }
    }


}