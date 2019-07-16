package com.yuejianzhong.latte_core.ui.refresh

import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import com.yuejianzhong.latte_core.app.Latte

public class RefreshHandler (refreshLayout:SwipeRefreshLayout):SwipeRefreshLayout.OnRefreshListener{

    private val REFRESH_LAYOUT = refreshLayout

    init {
        REFRESH_LAYOUT.setOnRefreshListener { this }
    }

    private fun refresh() {
        REFRESH_LAYOUT.isRefreshing = true
        Latte.getHandler().postDelayed({
            REFRESH_LAYOUT.isRefreshing = false
            Log.d("RefreshHandler","2000")
        },2000)
    }

    override fun onRefresh() {
        refresh()
    }

}