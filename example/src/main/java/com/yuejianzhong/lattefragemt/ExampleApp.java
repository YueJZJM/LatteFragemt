package com.yuejianzhong.lattefragemt;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.yuejianzhong.latte_core.app.Latte;
import com.yuejianzhong.latte_core.net.interceptors.DebugInterceptor;
import com.yuejianzhong.latte_ec.icon.FontEcModule;

import static com.yuejianzhong.lattefragemt.R.*;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://mock.fulingjie.com/mock-android/data/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())  //自定义字体 module
//                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        Logger.addLogAdapter(new AndroidLogAdapter());

    }
}
