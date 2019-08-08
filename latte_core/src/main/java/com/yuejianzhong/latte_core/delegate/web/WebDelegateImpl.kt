package com.yuejianzhong.latte_core.delegate.web

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.yuejianzhong.latte_core.delegate.IPageLoadListener
import com.yuejianzhong.latte_core.delegate.web.client.WebViewClientImpl
import com.yuejianzhong.latte_core.delegate.web.route.RouteKeys
import com.yuejianzhong.latte_core.delegate.web.route.Router

class WebDelegateImpl() : WebDelegate() {

    var pageLoadListener:IPageLoadListener? = null

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun initWebView(webView: WebView): WebView {
        return WebViewInitializer.createWebView(webView)
    }

    override fun initWebViewClient(): WebViewClient {
        val client = WebViewClientImpl(this)
        client.pageListener = this.pageLoadListener
        return client
    }

    override fun initWebChromeClient(): WebChromeClient {
        return WebChromeClient()
    }


    companion object{
        fun create(url:String):WebDelegateImpl{
            val args = Bundle()
            args.putString(RouteKeys.URL.name,url)
            val delegate = WebDelegateImpl()
            delegate.arguments = args
            return delegate
        }
    }



    override fun setInitializer(): IWebViewInitializer {
        return this
    }

    override fun setLayout(): Any {
        return webView!!
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        mUrl?.let {
            //用原生的方式模拟 web 跳转
            Router.instance.loadPage(this, it)
        }
    }

}