package com.yuejianzhong.latte_ec.main.discover

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.delegate.bottom.BottomItemDelegate
import com.yuejianzhong.latte_core.delegate.web.WebDelegateImpl
import com.yuejianzhong.latte_ec.R
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

class DiscoverDelegate : BottomItemDelegate() {
    override fun setLayout(): Any {
        return R.layout.delegate_discover
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        val delegate = WebDelegateImpl.create("index.html")
        supportDelegate.loadRootFragment(R.id.web_discovery_container,delegate)
        delegate.topDelegate = getParentDelegate()
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultHorizontalAnimator()
    }

}