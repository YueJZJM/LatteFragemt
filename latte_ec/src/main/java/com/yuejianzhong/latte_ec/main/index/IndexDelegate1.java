package com.yuejianzhong.latte_ec.main.index;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate;
import com.yuejianzhong.latte_core.ui.refresh.RefreshHandler;
import com.yuejianzhong.latte_core.ui.refresh.RefreshHandler1;
import com.yuejianzhong.latte_ec.R;
import com.yuejianzhong.latte_ec.R2;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import butterknife.BindView;

public class IndexDelegate1 extends BottomItemDelegate {

    private RefreshHandler mRefreshHandler;

    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;


    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    @Override
    public void onLazyInitView(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
    }

    @NotNull
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NotNull View rootView) {
//        mRefreshHandler = new RefreshHandler(mRefreshLayout);
//        Log.d("IndexDelegate1", mRefreshHandler.toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshHandler = new RefreshHandler(mRefreshLayout);
        Log.d("IndexDelegate1", mRefreshLayout.toString());
    }
}
