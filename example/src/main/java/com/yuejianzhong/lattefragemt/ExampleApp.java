package com.yuejianzhong.lattefragemt;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.yuejianzhong.latte_core.app.Latte;
import com.yuejianzhong.latte_ec.icon.FontEcModule;

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://mock.fulingjie.com/mock-android/data/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())  //自定义字体 module
                .configure();
    }
}
