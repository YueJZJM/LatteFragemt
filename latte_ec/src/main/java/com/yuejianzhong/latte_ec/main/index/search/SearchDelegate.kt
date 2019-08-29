package com.yuejianzhong.latte_ec.main.index.search

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import kotlinx.android.synthetic.main.delegate_search.*
import com.yuejianzhong.latte_core.util.storage.LattePreference
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.StringUtils
import com.yuejianzhong.latte_core.net.RestClient
import com.choices.divider.Divider
import android.support.v7.widget.LinearLayoutManager
import android.graphics.Color
import com.choices.divider.DividerItemDecoration




class SearchDelegate : LatteDelegate() {

    private var mAdapter: SearchAdapter? = null

    override fun setLayout(): Any {
        return com.yuejianzhong.latte_ec.R.layout.delegate_search
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_top_search.setOnClickListener{
            val searchItemText = et_search_view1.text.toString()
            onClickSearch(searchItemText)
        }
        icon_top_search_back.setOnClickListener {
            supportDelegate.pop()
        }


        val manager = LinearLayoutManager(context)
        rv_search.layoutManager = manager

        val data = SearchDataConverter().conver()
        mAdapter = SearchAdapter(data)
        rv_search.adapter = mAdapter

        val itemDecoration = DividerItemDecoration()
        itemDecoration.setDividerLookup(object : DividerItemDecoration.DividerLookup {
            override fun getVerticalDivider(position: Int): Divider? {
                return null
            }

            override fun getHorizontalDivider(position: Int): Divider {
                return Divider.Builder()
                        .size(2)
                        .margin(20, 20)
                        .color(Color.GRAY)
                        .build()
            }
        })
        rv_search.addItemDecoration(itemDecoration)

    }

    override fun onResume() {
        super.onResume()
        et_search_view1.isFocusable = true
    }

    //服务器搜索 返回json 然后解析
    private fun onClickSearch(searchItemText: String) {
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/api/search.php?key=$searchItemText")
                .loader(context)
                .success {
                    saveItem(searchItemText)
                    et_search_view1.setText("")
                    //展示一些东西
                    //弹出一段话
                }
                .build()
                .get()
    }

    private fun saveItem(item: String) {
        if (!StringUtils.isEmpty(item) && !StringUtils.isEmpty(item)) {
            val history: MutableList<String>
            val historyStr = LattePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY)
            if (StringUtils.isEmpty(historyStr)) {
                history = ArrayList()
            } else {
                history = JSON.parseObject<List<String>>(historyStr, ArrayList::class.java) as MutableList<String>
            }
            history.add(item)
            val json = JSON.toJSONString(history)
            LattePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json)
        }
    }


}