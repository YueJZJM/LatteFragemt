package com.yuejianzhong.latte_core.delegate.web.client

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import com.yuejianzhong.latte_core.delegate.IPageLoadListener
import com.yuejianzhong.latte_core.delegate.web.WebDelegate
import com.yuejianzhong.latte_core.delegate.web.route.Router
import com.yuejianzhong.latte_core.ui.loader.LatteLoader

class WebViewClientImpl(delegate: WebDelegate) : WebViewClient() {

    private  val DELEGATE = delegate
    var pageListener:IPageLoadListener? = null

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return Router.instance.handleWebUrl(DELEGATE, url!!)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        pageListener?.onLoadStart()
        LatteLoader.showLoading(view?.context)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        pageListener?.onLoadEnd()
        LatteLoader.stopLoading()
    }

}