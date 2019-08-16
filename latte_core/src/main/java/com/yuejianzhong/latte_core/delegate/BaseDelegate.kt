package com.yuejianzhong.latte_core.delegate

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yuejianzhong.latte_core.activity.ProxyActivity

import butterknife.ButterKnife
import butterknife.Unbinder
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragmentDelegate
import me.yokeyword.fragmentation.anim.FragmentAnimator
import android.view.animation.Animation
import android.app.Activity
import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import me.yokeyword.fragmentation.ExtraTransaction
import android.support.v4.app.FragmentActivity





abstract class BaseDelegate : Fragment(),ISupportFragment {

    private var mRootView: View? = null

    private var mUnbinder: Unbinder? = null

    private val DELEGATE = SupportFragmentDelegate(this)


//    val proxyActivity: ProxyActivity
//        get() = _mActivity as ProxyActivity

    protected var _mActivity: FragmentActivity? = null

    abstract fun setLayout(): Any

    abstract fun onBindView(savedInstanceState: Bundle?, rootView: View)

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }

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



    fun getProxyActivity(): ProxyActivity {
        return _mActivity as ProxyActivity
    }



    override fun post(runnable: Runnable) {
        DELEGATE.post(runnable)
    }

    override fun getSupportDelegate(): SupportFragmentDelegate {
        return DELEGATE
    }

    override fun extraTransaction(): ExtraTransaction {
        return DELEGATE.extraTransaction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DELEGATE.onAttach(context as Activity)
        _mActivity = DELEGATE.activity
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DELEGATE.onCreate(savedInstanceState)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return DELEGATE.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DELEGATE.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(@NonNull outState: Bundle) {
        super.onSaveInstanceState(outState)
        DELEGATE.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        DELEGATE.onResume()
    }

    override fun onPause() {
        super.onPause()
        DELEGATE.onPause()
    }


    override fun onDestroyView() {
        DELEGATE.onDestroyView()
        super.onDestroyView()
        if (mUnbinder != null) {
            mUnbinder!!.unbind()
        }
    }

    override fun onDestroy() {
        DELEGATE.onDestroy()
        super.onDestroy()
    }



    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        DELEGATE.onHiddenChanged(hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        DELEGATE.setUserVisibleHint(isVisibleToUser)
    }

    override fun enqueueAction(runnable: Runnable) {
        DELEGATE.enqueueAction(runnable)
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        DELEGATE.onEnterAnimationEnd(savedInstanceState)
    }

    override fun onLazyInitView(@Nullable savedInstanceState: Bundle?) {
        DELEGATE.onLazyInitView(savedInstanceState)
    }

    override fun onSupportVisible() {
        DELEGATE.onSupportVisible()
    }

    override fun onSupportInvisible() {
        DELEGATE.onSupportInvisible()
    }

    override fun isSupportVisible(): Boolean {
        return DELEGATE.isSupportVisible
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DELEGATE.onCreateFragmentAnimator()
    }

    override fun getFragmentAnimator(): FragmentAnimator {
        return DELEGATE.fragmentAnimator
    }

    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator) {
        DELEGATE.fragmentAnimator = fragmentAnimator
    }

    override fun onBackPressedSupport(): Boolean {
        return DELEGATE.onBackPressedSupport()
    }

    override fun setFragmentResult(resultCode: Int, bundle: Bundle) {
        DELEGATE.setFragmentResult(resultCode, bundle)
    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle) {
        DELEGATE.onFragmentResult(requestCode, resultCode, data)
    }

    override fun onNewBundle(args: Bundle) {
        DELEGATE.onNewBundle(args)
    }

    override fun putNewBundle(newBundle: Bundle) {
        DELEGATE.putNewBundle(newBundle)
    }

    fun start(toFragment: ISupportFragment) {
        DELEGATE.start(toFragment)
    }

    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        DELEGATE.start(toFragment, launchMode)
    }

    fun pop() {
        DELEGATE.pop()
    }
}
