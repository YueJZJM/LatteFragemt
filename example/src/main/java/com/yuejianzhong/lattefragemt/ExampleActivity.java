package com.yuejianzhong.lattefragemt;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yuejianzhong.latte_core.activity.ProxyActivity;
import com.yuejianzhong.latte_core.delegate.LatteDelegate;
import com.yuejianzhong.latte_core.ui.launcher.ILauncherListener;
import com.yuejianzhong.latte_core.ui.launcher.OnLauncherFinishTag;
import com.yuejianzhong.latte_ec.launcher.LauncherDelegate;
import com.yuejianzhong.latte_ec.launcher.LauncherScrollDelegate;
import com.yuejianzhong.latte_ec.main.EcBottomDelegate;
import com.yuejianzhong.latte_ec.sign.ISignListener;
import com.yuejianzhong.latte_ec.sign.SignInDelegate;
import com.yuejianzhong.latte_ec.sign.SignUpDelegate;

public class ExampleActivity extends ProxyActivity implements ISignListener,ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:

                Toast.makeText(this,"用户登录了",Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:

                Toast.makeText(this,"用户没有登录",Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            default:
                break;
        }
    }
}
