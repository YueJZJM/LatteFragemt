package com.yuejianzhong.latte_core.delegate

import android.os.Bundle
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yuejianzhong.latte_core.activity.ProxyActivity

import butterknife.ButterKnife
import butterknife.Unbinder
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment

abstract class BaseDelegate : SwipeBackFragment() {

    private var mRootView: View? = null

    private var mUnbinder: Unbinder? = null

    val proxyActivity: ProxyActivity
        get() = _mActivity as ProxyActivity

    abstract fun setLayout(): Any

    abstract fun onBindView(savedInstanceState: Bundle?, rootView: View)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View
        if (setLayout() is Int) {
            rootView = inflater.inflate(setLayout() as Int, container, false)
        } else if (setLayout() is View) {
            rootView = setLayout() as View
        } else {
            throw ClassCastException("setLayout() type must be int or view")
        }
        mRootView = rootView
        mUnbinder = ButterKnife.bind(this, rootView)
        onBindView(savedInstanceState, rootView)
        return rootView
    }

    fun <T : View> `$`(@IdRes viewId: Int): T {
        if (mRootView != null) {
            return mRootView!!.findViewById(viewId)
        }
        throw NullPointerException("rootView is null")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mUnbinder != null) {
            mUnbinder!!.unbind()
        }
    }
}
