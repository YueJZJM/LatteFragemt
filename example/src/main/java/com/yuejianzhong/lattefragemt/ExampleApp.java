package com.yuejianzhong.lattefragemt;

import android.app.Application;
import android.support.v4.os.TraceCompat;

import com.blankj.utilcode.util.LogUtils;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.yuejianzhong.latte_core.app.Latte;
import com.yuejianzhong.latte_core.util.callback.CallbackManager;
import com.yuejianzhong.latte_core.util.callback.CallbackType;
import com.yuejianzhong.latte_core.util.callback.IGlobalCallback;
import com.yuejianzhong.latte_ec.icon.FontEcModule;

import cn.jpush.android.api.JPushInterface;

import static com.yuejianzhong.lattefragemt.R.*;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TraceCompat.beginSection("AppOncreate");
        Latte.init(this)
                .withApiHost("http://mock.fulingjie.com/mock-android/data/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())  //自定义字体 module
//                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        Logger.addLogAdapter(new AndroidLogAdapter());
        TraceCompat.endSection();

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //子模块关闭推送 反向控制主模块关闭推送 接口实现
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, args -> {
                    if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                        //开启极光推送
                        JPushInterface.setDebugMode(true);
                        JPushInterface.init(Latte.getApplicationContext());
                    }
                    LogUtils.d(args.toString());
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, args -> {
                    if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                        JPushInterface.stopPush(Latte.getApplicationContext());
                    }
                    LogUtils.d(args.toString());
                });
    }
}

