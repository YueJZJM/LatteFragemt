package com.yuejianzhong.latte_ec.main.sort.content

import android.os.Bundle
import android.util.Log
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_core.net.RestClient
import com.yuejianzhong.latte_ec.R
import com.yuejianzhong.latte_ec.R.id.rv_list_content
import kotlinx.android.synthetic.main.delegate_list_content.*

class ContentDelegate : LatteDelegate() {

    private var mContentId = -1

    private var mData : List<SectionBean>? = null

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
            Log.d("ContentDelegate", mContentId.toString())
        }
    }

    override fun setLayout(): Any {
        return R.layout.delegate_list_content
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {
        initData()
    }


    fun initData() {
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/sort_content_data_1.json")
                .success {
                    Log.d("yuejz",it)
                    mData = SectionDataConverter().conver(it)
                    val sectionAdapter = SectionAdapter(R.layout.item_section_content,
                            R.layout.item_section_header,
                            mData!!)
                    rv_list_content.adapter = sectionAdapter

                }
                .build().get()
    }
}