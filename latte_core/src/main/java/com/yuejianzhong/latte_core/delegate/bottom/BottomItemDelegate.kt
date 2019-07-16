package com.yuejianzhong.latte_core.delegate.bottom

import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.widget.Toast
import com.yuejianzhong.latte_core.R
import com.yuejianzhong.latte_core.delegate.LatteDelegate

public abstract class BottomItemDelegate : LatteDelegate(), OnKeyListener {

    private var mExitTime: Long = 0
    private val EXIT_TIME: Int = 2000

    override fun onResume() {
        super.onResume()
        var rootView = view
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true)
//            rootView.isFocusableInTouchMode
            rootView.requestFocus()
            rootView.setOnKeyListener(this)
        }
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > mExitTime) {

                Toast.makeText(context, "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show()
                mExitTime = System.currentTimeMillis()

            } else {
                _mActivity.finish()
                if (mExitTime == 0L) {
                    mExitTime=0
                }
            }
            return true
        }
        return false
    }
}