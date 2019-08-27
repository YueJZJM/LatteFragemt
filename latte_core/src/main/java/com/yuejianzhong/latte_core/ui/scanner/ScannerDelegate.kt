package com.yuejianzhong.latte_core.ui.scanner

import android.os.Bundle
import android.view.View
import com.orhanobut.logger.Logger
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.ui.camera.RequestCodes
import com.yuejianzhong.latte_core.util.callback.CallbackManager
import com.yuejianzhong.latte_core.util.callback.CallbackType
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScannerDelegate : LatteDelegate(),ZBarScannerView.ResultHandler {
    override fun handleResult(result: Result?) {
        val bundle = Bundle()
        bundle.putString("SCAN_RESULT", result?.contents)
        setFragmentResult(RequestCodes.SCAN, bundle)

        val callback = CallbackManager
                .getInstance()
                .getCallback(CallbackType.ON_SCAN)
        result?.contents?.let { callback?.executeCallback(it) }
        supportDelegate.pop()
    }

    private var mScanView: ScanView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (mScanView == null) {
            mScanView = context?.let {
                ScanView(it)

            }
        }
        Logger.d(mScanView)
        mScanView?.setAutoFocus(true)
        mScanView?.setResultHandler { this@ScannerDelegate }
    }


    override fun setLayout(): Any {
        return mScanView!!
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        mScanView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScanView?.let {
            it.stopCameraPreview()
            it.stopCamera()
        }
    }

}