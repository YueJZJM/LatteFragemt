package com.yuejianzhong.latte_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.yuejianzhong.latte_core.app.AccountManager;
import com.yuejianzhong.latte_core.app.IUserChecker;
import com.yuejianzhong.latte_core.delegate.LatteDelegate;
import com.yuejianzhong.latte_core.ui.launcher.ILauncherListener;
import com.yuejianzhong.latte_core.ui.launcher.OnLauncherFinishTag;
import com.yuejianzhong.latte_core.ui.launcher.ScrollLauncherTag;
import com.yuejianzhong.latte_core.util.storage.LattePreference;
import com.yuejianzhong.latte_core.util.timer.BaseTimerTask;
import com.yuejianzhong.latte_core.util.timer.ITimerListerer;
import com.yuejianzhong.latte_ec.R;
import com.yuejianzhong.latte_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


public class LauncherDelegate extends LatteDelegate implements ITimerListerer {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 3;

    private ILauncherListener mLauncherListener;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimer() {

    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mLauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录了 APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mLauncherListener != null) {
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mLauncherListener != null) {
                        mLauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过{0}s",mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
