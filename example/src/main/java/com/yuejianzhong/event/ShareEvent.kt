package com.yuejianzhong.event

import com.yuejianzhong.latte_core.delegate.web.event.Event
import cn.sharesdk.onekeyshare.OnekeyShare
import com.alibaba.fastjson.JSON
import com.blankj.utilcode.util.LogUtils


class ShareEvent : Event() {
    override fun execute(params: String): String? {
        LogUtils.d("ShareEvent", params)

        val `object` = JSON.parseObject(params).getJSONObject("params")
        val title = `object`.getString("title")
        val url = `object`.getString("url")
        val imageUrl = `object`.getString("imageUrl")
        val text = `object`.getString("text")

        val oks = OnekeyShare()
        oks.disableSSOWhenAuthorize()
        oks.setTitle(title)
        oks.text = text
        oks.setImageUrl(imageUrl)
        oks.setUrl(url)
        oks.show(context)
        return "aa"
    }

}