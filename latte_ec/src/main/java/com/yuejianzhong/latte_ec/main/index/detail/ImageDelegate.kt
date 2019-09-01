package com.yuejianzhong.latte_ec.main.index.detail

import android.os.Bundle
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.ItemType
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.yuejianzhong.latte_core.delegate.LatteDelegate
import com.yuejianzhong.latte_ec.R
import kotlinx.android.synthetic.main.delegate_image.*


class ImageDelegate : LatteDelegate() {
    override fun onBindView(savedInstanceState: Bundle?, rootView: View) {

    }

//    private var mRecyclerView: RecyclerView? = null

    override fun setLayout(): Any {
        return R.layout.delegate_image
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = LinearLayoutManager(context)
        rv_image_container.layoutManager = manager
        initImages()
    }


    private fun initImages() {
        val arguments = getArguments()
        if (arguments != null) {
            val pictures = arguments.getStringArrayList(ARG_PICTURES)
            val entities = ArrayList<MultipleItemEntity>()
            val size: Int
            if (pictures != null) {
                size = pictures.size
                for (i in 0 until size) {
                    val imageUrl = pictures[i]
                    val entity = MultipleItemEntity
                            .builder()
                            .setItemType(ItemType.SINGLE_BIG_IMAGE)
                            .setField(MultipleFields.IMAGE_URL, imageUrl)
                            .build()
                    entities.add(entity)
                }
                val adapter = RecyclerImageAdapter(entities)
                rv_image_container.adapter = adapter
            }
        }
    }

    companion object {

        private val ARG_PICTURES = "ARG_PICTURES"

        fun create(pictures: ArrayList<String>): ImageDelegate {
            val args = Bundle()
            args.putStringArrayList(ARG_PICTURES, pictures)
            val delegate = ImageDelegate()
            delegate.arguments = args
            return delegate
        }
    }

}
