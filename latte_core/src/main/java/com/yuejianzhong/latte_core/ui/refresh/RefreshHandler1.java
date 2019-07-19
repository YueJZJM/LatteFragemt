package com.yuejianzhong.latte_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.yuejianzhong.latte_core.app.Latte;

public class RefreshHandler1 implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler1(SwipeRefreshLayout swipeRefreshLayout) {
        REFRESH_LAYOUT = swipeRefreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }


    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
//        Latte.getHandler().postDelayed({
//                REFRESH_LAYOUT.setRefreshing(false);
//        Log.d("RefreshHandler", "2000");
//        }, 2000);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Latte.getHandler().postDelayed({
                REFRESH_LAYOUT.setRefreshing(false);
                Log.d("RefreshHandler", "2000");
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        refresh();
        Log.d("Refresh1:onRefresh", "aa");
    }
}
