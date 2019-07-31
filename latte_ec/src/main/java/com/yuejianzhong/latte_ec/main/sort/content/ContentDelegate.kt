package com.yuejianzhong.latte_ec.main.sort.content

import android.os.Bundle
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R

class ContentDelegate : LatteDelegate() {

    private var mContentId = -1

    companion object{
        val ARG_CONTENT_ID = "CONTENT_ID"
        fun newInstance(contentId: Int): ContentDelegate {
            val arge = Bundle()
            arge.putInt(ARG_CONTENT_ID, contentId)
            val delegate: ContentDelegate = ContentDelegate()
            delegate.arguments = arge
            return delegate
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arge = arguments
        arge?.let {
            mContentId = it.getInt(ARG_CONTENT_ID)
        }
    }

    override fun setLayout(): Any {
        return R.layout.delegate_list_content
    }

    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
    }

}