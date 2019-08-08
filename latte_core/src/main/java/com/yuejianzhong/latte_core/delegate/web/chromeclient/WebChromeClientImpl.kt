package com.yuejianzhong.latte_core.delegate.web.chromeclient

import android.webkit.JsResult
import android.webkit.WebView
import android.webkit.WebChromeClient


class WebChromeClientImpl : WebChromeClient() {

    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
        return super.onJsAlert(view, url, message, result)
    }
}