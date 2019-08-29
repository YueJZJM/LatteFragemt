package com.yuejianzhong.latte_core.delegate.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import com.yuejianzhong.latte_core.app.Latte
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.delegate.web.route.RouteKeys
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

abstract class WebDelegate : LatteDelegate(),IWebViewInitializer {
    public var webView:WebView? = null
    private val referenceQueue = ReferenceQueue<WebView>()
    var mUrl:String? = null
    protected var mIsWebViewAbailable = false
    var  topDelegate: LatteDelegate? = null
        get() {
//        if (topDelegate == null){
//            topDelegate = this
//        }
//        return topDelegate
            return topDelegate?:return this
    }

    public abstract fun setInitializer(): IWebViewInitializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        mUrl = args?.getString(RouteKeys.URL.name) as String
        initWebView()

    }

    @SuppressLint("JavascriptInterface")
    fun initWebView() {
        if (webView != null) {
            webView!!.removeAllViews()
            webView!!.destroy()
        } else {
            val initializer = setInitializer()
            if (initializer != null) {
                val webViewWeakReference = WeakReference<WebView>(WebView(context), referenceQueue)
                webView = webViewWeakReference.get();
                webView = webView?.let { initializer.initWebView(it) };
                webView!!.setWebViewClient(initializer.initWebViewClient());
                webView!!.setWebChromeClient(initializer.initWebChromeClient());

                webView!!.addJavascriptInterface(LatteWebInterface.create(this), "latte");
                mIsWebViewAbailable = true;
            }else{
                throw NullPointerException("Initializer is null")
            }
        }
    }

//    fun getUrl():String{
//        return mUrl
//    }
//
//    public fun getWebView():WebView{
//        webView?.let {
//           if (mIsWebViewAbailable) {
//               return it
//
//           }else{}
//        }
//    }

    override fun onPause() {
        super.onPause()
        webView?.let { it.onPause() }
    }

    override fun onResume() {
        super.onResume()
        if (webView != null) {
            webView?.onResume()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mIsWebViewAbailable = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (webView != null) {
            webView?.removeAllViews()
            webView?.destroy()
            webView = null
        }
    }
}