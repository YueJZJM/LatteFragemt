package com.yuejianzhong.latte_core.delegate.web.route

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.webkit.URLUtil
import android.webkit.WebView

import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.delegate.web.WebDelegate
import com.yuejianzhong.latte_core.delegate.web.WebDelegateImpl

class Router private constructor() {

    private object Holder {
        val INSTANCE = Router()
    }

    fun handleWebUrl(delegate: WebDelegate, url: String): Boolean {

        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.context, url)
            return true
        }

//        val topDelegate = delegate.topFragment
//
//        val webDelegate = WebDelegateImpl.create(url)
//        topDelegate.getSupportDelegate().start(webDelegate)

        val topdelegate = delegate.topDelegate
        val webDelegate = WebDelegateImpl.create(url)
        topdelegate?.start(webDelegate)
        return true
    }

    private fun loadWebPage(webView: WebView?, url: String) {
        if (webView != null) {
            webView.loadUrl(url)
        } else {
            throw NullPointerException("WebView is null!")
        }
    }

    private fun loadLocalPage(webView: WebView?, url: String) {
        loadWebPage(webView, "file:///android_asset/$url")
    }

    private fun loadPage(webView: WebView?, url: String) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url)
        } else {
            loadLocalPage(webView, url)
        }
    }

    fun loadPage(delegate: WebDelegate, url: String) {
        loadPage(delegate.webView, url)
    }

    private fun callPhone(context: Context?, uri: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        val data = Uri.parse(uri)
        intent.data = data
        ContextCompat.startActivity(context!!, intent, null)
    }

    companion object {

        val instance = Holder.INSTANCE

    }
}
