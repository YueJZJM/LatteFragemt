package com.yuejianzhong.latte_core.delegate.web

import android.webkit.JavascriptInterface
import com.alibaba.fastjson.JSON
import com.yuejianzhong.latte_core.delegate.web.event.EventManager

class LatteWebInterface private constructor(webDelegate: WebDelegate){

    private val DELEGATE = webDelegate


    companion object{
        fun create(webDelegate: WebDelegate): LatteWebInterface {
            return LatteWebInterface(webDelegate)
        }
    }

    @JavascriptInterface
    fun event(params: String):String? {
        val action = JSON.parseObject(params).getString("action");
        val event = EventManager.instance.createEvent(action)
        event?.let {
            it.action = action
            it.delegate = DELEGATE
            it.context = DELEGATE.context!!
            it.url = DELEGATE.mUrl!!
            return it.execute(params)
        }
//        return null
    }

}