package com.yuejianzhong.latte_core.delegate.bottom

import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.widget.Toast
import com.yuejianzhong.latte_core.R
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.app.Latte



public abstract class BottomItemDelegate : LatteDelegate() {

//    private var mExitTime: Long = 0
//    private val EXIT_TIME: Int = 2000
    // 再点一次退出程序时间设置
    private val WAIT_TIME = 2000L
    private var TOUCH_TIME: Long = 0


//    override fun onResume() {
//        super.onResume()
//        var rootView = view
//        if (rootView != null) {
//            rootView.setFocusableInTouchMode(true)
////            rootView.isFocusableInTouchMode
//            rootView.requestFocus()
//            rootView.setOnKeyListener(this)
//        }
//    }

    override fun onBackPressedSupport(): Boolean {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity!!.finish()
        } else {
            TOUCH_TIME = System.currentTimeMillis()
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show()
        }
        return true
    }


}