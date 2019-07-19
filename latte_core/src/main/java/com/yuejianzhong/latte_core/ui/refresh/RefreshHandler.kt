package com.yuejianzhong.latte_core.ui.refresh

import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.widget.Toast
import com.yuejianzhong.latte_core.app.Latte
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_core.net.callback.ISuccess

public class RefreshHandler (refreshLayout:SwipeRefreshLayout){

    private val REFRESH_LAYOUT = refreshLayout

    init {
//        REFRESH_LAYOUT.setOnRefreshListener { this }
        REFRESH_LAYOUT.setOnRefreshListener {
            refresh()
        }
        Log.d("RefreshHandler",refreshLayout.toString())
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
        RestClient.builder()
                .url(url)
                .success(ISuccess { response->
//                    Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_SHORT).show()

                })
                .build()
                .get()
    }

}