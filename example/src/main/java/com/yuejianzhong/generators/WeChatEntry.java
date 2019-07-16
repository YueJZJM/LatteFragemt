package com.yuejianzhong.generators;


import com.yuejianzhong.latte_annotations.annotations.EntryGenerator;
import com.yuejianzhong.latte_core.wechat.templates.WXEntryTemplate;

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.yuejianzhong.lattefragemt",
        entryTemplate =  WXEntryTemplate.class
)
public interface WeChatEntry {
}
