package com.yuejianzhong.latte_core.delegate.web

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

interface IWebViewInitializer {
    fun initWebView(webView: WebView):WebView

    fun initWebViewClient(): WebViewClient

    fun initWebChromeClient():WebChromeClient
}