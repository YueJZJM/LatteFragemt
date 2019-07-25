package com.yuejianzhong.latte_core.ui.refresh

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yuejianzhong.latte_core.app.Latte
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_core.net.callback.ISuccess
import com.yuejianzhong.latte_core.ui.recycler.DataConverter
import com.yuejianzhong.latte_core.ui.recycler.MultipleRecyclerAdapter

public class RefreshHandler private constructor(private val REFRESH_LAYOUT:SwipeRefreshLayout,
                             private val RECYCLERVIEW:RecyclerView,
                             private val CONVERTER:DataConverter,
                             private val bean: PagingBean):BaseQuickAdapter.RequestLoadMoreListener{
    override fun onLoadMoreRequested() {

    }

    private lateinit var mAdapter:MultipleRecyclerAdapter

    companion object{
        public fun create(  REFRESH_LAYOUT: SwipeRefreshLayout,
                            RECYCLERVIEW: RecyclerView,
                            CONVERTER: DataConverter
                            ): RefreshHandler {
            return RefreshHandler(REFRESH_LAYOUT,RECYCLERVIEW,CONVERTER,PagingBean())

        }
    }

    init {
//        REFRESH_LAYOUT.setOnRefreshListener { this }
        REFRESH_LAYOUT.setOnRefreshListener {
            refresh()
        }
        Log.d("RefreshHandler",REFRESH_LAYOUT.toString())
    }

    private fun refresh() {
        REFRESH_LAYOUT.isRefreshing = true
        Latte.getHandler().postDelayed({
            REFRESH_LAYOUT.isRefreshing = false
            Log.d("RefreshHandler","2000")
        },2000)
    }

//    override fun onRefresh() {
//        refresh()
//    }

    public fun firstPage(url:String) {
        bean.setDelayed(1000)
        RestClient.builder()
                .url(url)
                .success(ISuccess { response->
//                    Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_SHORT).show()
                    val jsonObject = JSON.parseObject(response)
                    bean.setTotal(jsonObject.getIntValue("total"))
                            .setPageSize(jsonObject.getInteger("page_size"))
                    //设置 Adapter
                    mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response))
                    mAdapter.setOnLoadMoreListener(this@RefreshHandler, RECYCLERVIEW)
                    RECYCLERVIEW.adapter = mAdapter
                    bean.addIndex()

                })
                .build()
                .get()
    }

}