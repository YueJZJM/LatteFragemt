package com.yuejianzhong.generators;


import com.yuejianzhong.latte_annotations.annotations.AppRegisterGenerator;
import com.yuejianzhong.latte_core.wechat.templates.AppRegisterTemplate;

@AppRegisterGenerator(
        packageName = "com.yuejianzhong.lattefragemt",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
