package com.yuejianzhong.lattefragemt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yuejianzhong.latte_core.activity.ProxyActivity;
import com.yuejianzhong.latte_core.delegate.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

}
