package com.yuejianzhong.generators;


import com.yuejianzhong.latte_annotations.annotations.PayEntryGenerator;
import com.yuejianzhong.latte_core.wechat.templates.WXPayEntryTemplate;

@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.yuejianzhong.lattefragemt",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
