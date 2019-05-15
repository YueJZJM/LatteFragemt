package com.yuejianzhong.latte_core.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.widget.FrameLayout;

import com.yuejianzhong.latte_core.R;
import com.yuejianzhong.latte_core.delegate.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(Bundle savedInstanceState) {
        final FrameLayout content = new FrameLayout(this);
        content.setId(R.id.delegate_content);

        setContentView(content);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_content, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
