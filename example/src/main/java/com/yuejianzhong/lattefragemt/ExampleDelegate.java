package com.yuejianzhong.lattefragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yuejianzhong.latte_core.delegate.LatteDelegate;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
