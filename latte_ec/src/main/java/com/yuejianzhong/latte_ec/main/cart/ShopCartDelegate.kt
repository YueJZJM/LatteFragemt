package com.yuejianzhong.latte_ec.main.cart

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_ec.R
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import kotlinx.android.synthetic.main.delegate_shop_cart.*
import kotlinx.android.synthetic.main.stub_shop_cart_no_item.*
import android.support.v4.content.ContextCompat
import com.blankj.utilcode.util.ToastUtils
import android.support.v7.widget.ViewStubCompat
import com.yuejianzhong.latte_ec.main.EcBottomDelegate


class ShopCartDelegate : BottomItemDelegate(),ISuccess, ICartItemListener,View.OnClickListener  {


    override fun onItemClick(itemTotalPrice: Double) {
        val price = mAdapter?.totalPrice
        tv_shop_cart_total_price.text = price.toString()
    }

    private var mAdapter: ShopCartAdapter? = null
    //购物车数量标记
    private var mCurrentCount = 0
    private val mTotalCount = 0
    private var mTotalPrice = 0.00

    private val mStubNoItem: ViewStubCompat? = null
    override fun setLayout(): Any {
        return R.layout.delegate_shop_cart
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        icon_shop_cart_select_all.tag = 0
        icon_shop_cart_select_all.setOnClickListener(this)
        tv_top_shop_cart_remove_selected.setOnClickListener(this)
        tv_top_shop_cart_clear.setOnClickListener(this)
        tv_shop_cart_pay.setOnClickListener(this)

    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/shop_cart_data.json")
                .loader(context)
                .success {
                    val data = ShopCartDataConverter()
                            .setJsonData(it)
                            .conver()
//                    Log.d("aaaaa","aaa")
                    mAdapter = ShopCartAdapter(data)
                    mAdapter!!.setCartItemListener(this)
                    val manager = LinearLayoutManager(context)
                    rv_shop_cart.layoutManager = manager
                    rv_shop_cart.adapter = mAdapter
                    mTotalPrice = mAdapter!!.totalPrice
                    tv_shop_cart_total_price.text = mTotalPrice.toString();
                }
                .build()
                .get()


    }


    override fun onClick(v: View?) {
        Log.d("yujz","aaa")
        when (v) {
            //全选点击控件
            icon_shop_cart_select_all ->  onClickSelectAll()
            //标题栏删除控件
            tv_top_shop_cart_remove_selected -> onClickRemoveSelectedItem()
            tv_top_shop_cart_clear -> onClickClear()
            tv_shop_cart_pay -> createOrder()
        }
    }

    private fun createOrder() {

    }

    private fun onClickClear() {
        val data = mAdapter?.data
        if (data!!.size != 0) {
            data.clear()
            mAdapter?.notifyDataSetChanged()
            checkItemCount()
        } else {
            ToastUtils.showShort("空空如也，赶紧购物吧！")
        }
    }

    private fun onClickRemoveSelectedItem() {
        val data = mAdapter?.data
        if (data?.size != 0) {
            //要删除的数据
            val deleteEntities = ArrayList<MultipleItemEntity>()
            if (data != null) {
                for (entity in data) {
                    val isSelected = entity.getField(ShopCartItemFields.IS_SELECTED)!! as Boolean
                    if (isSelected) {
                        deleteEntities.add(entity)
                    }
                }
            }
            for (entity in deleteEntities) {
                val removePosition: Int
                val entityPosition = entity.getField(ShopCartItemFields.POSITION)!! as Int
                removePosition = if (entityPosition > mCurrentCount - 1) {
                    entityPosition - (mTotalCount - mCurrentCount)
                } else {
                    entityPosition
                }
                if (removePosition <= mAdapter!!.itemCount) {
                    mAdapter?.remove(removePosition)
                    mCurrentCount = mAdapter!!.itemCount
                    //更新数据
                    mAdapter?.notifyItemRangeChanged(removePosition, mAdapter!!.itemCount)
                }
            }
            checkItemCount()
        } else {
            ToastUtils.showShort("空空如也，赶紧购物吧！")
        }
    }

    @SuppressLint("RestrictedApi")
    private fun checkItemCount() {
        val count = mAdapter?.itemCount
        if (count == 0) {
            stub_no_item.inflate()
//            val tvToBuy = stubView.findViewById(R.id.tv_stub_to_buy)

            ToastUtils.showShort("你该购物啦！")
            tv_stub_to_buy.setOnClickListener {
                //切换到首页
                val indexTab = 0
                val ecBottomFragment:EcBottomDelegate = getParentDelegate()
                ecBottomFragment.setCurrentFragment(0)
                val indexFragment = ecBottomFragment.getItemFragments()[indexTab]
                ecBottomFragment.supportDelegate.showHideFragment(indexFragment, this@ShopCartDelegate)
                ecBottomFragment.changeColor(indexTab)
            }
            rv_shop_cart.visibility = View.GONE
        } else {
            rv_shop_cart.visibility = View.VISIBLE
        }
    }


    private fun onClickSelectAll() {
        val tag = icon_shop_cart_select_all.tag as Int
        if (tag == 0) {
            val context = context
            if (context != null) {
                icon_shop_cart_select_all.setTextColor(ContextCompat.getColor(context, R.color.app_main))
            }
            icon_shop_cart_select_all.tag = 1
            this.mAdapter?.setIsSelectedAll(true)
            //更新RecyclerView的显示状态
            this.mAdapter?.notifyItemRangeChanged(0, mAdapter!!.itemCount)
        } else {
            icon_shop_cart_select_all.setTextColor(Color.GRAY)
            icon_shop_cart_select_all.tag = 0
            this.mAdapter?.setIsSelectedAll(false)
            this.mAdapter?.notifyItemRangeChanged(0, mAdapter!!.itemCount)
        }
    }

    override fun onSuccess(response: String) {
//        val data = ShopCartDataConverter()
//                .setJsonData(response)
//                .conver()
//        Log.d("aaaaa","aaa")
//        mAdapter = ShopCartAdapter(data)
//        mAdapter!!.setCartItemListener(this)
//        val manager = LinearLayoutManager(context)
//        rv_shop_cart.layoutManager = manager
//        rv_shop_cart.adapter = mAdapter
//        mTotalPrice = mAdapter.getTotalPrice()
//        mTvTotalPrice.setText(String.valueOf(mTotalPrice))
//        checkItemCount()
    }

}