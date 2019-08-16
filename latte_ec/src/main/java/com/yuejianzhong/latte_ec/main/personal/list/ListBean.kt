package com.yuejianzhong.latte_ec.main.personal.list

import android.widget.CompoundButton
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.yuejianzhong.latte_core.delegate.LatteDelegate


class ListBean(mItemType: Int, mImageUrl: String?, mText: String?, mValue: String?,
               mId: Int, mFragment: LatteDelegate?, mOnCheckedChangeListener: CompoundButton.OnCheckedChangeListener?) : MultiItemEntity {

    private var mItemType = 0
    var imageUrl: String? = null
    private var mText: String? = null
    private var mValue: String? = null
    var id = 0
    var fragment: LatteDelegate? = null
    private var mOnCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null

    val text: String
        get() = mText ?: ""

    val value: String
        get() {
            return mValue ?: ""
        }

    init {
        this.mItemType = mItemType
        this.imageUrl = mImageUrl
        this.mText = mText
        this.mValue = mValue
        this.id = mId
        this.fragment = mFragment
        this.mOnCheckedChangeListener = mOnCheckedChangeListener
    }

    fun getmOnCheckedChangeListener(): CompoundButton.OnCheckedChangeListener? {
        return mOnCheckedChangeListener
    }

    override fun getItemType(): Int {
        return mItemType
    }

    class Builder {

        private var id = 0
        private var itemType = 0
        private var imageUrl: String? = null
        private var text: String? = null
        private var value: String? = null
        private var onCheckedChangeListener: CompoundButton.OnCheckedChangeListener? = null
        private var fragment: LatteDelegate? = null

        fun setId(id: Int): Builder {
            this.id = id
            return this
        }

        fun setItemType(itemType: Int): Builder {
            this.itemType = itemType
            return this
        }

        fun setImageUrl(imageUrl: String): Builder {
            this.imageUrl = imageUrl
            return this
        }

        fun setText(text: String): Builder {
            this.text = text
            return this
        }

        fun setValue(value: String): Builder {
            this.value = value
            return this
        }

        fun setOnCheckedChangeListener(onCheckedChangeListener: CompoundButton.OnCheckedChangeListener): Builder {
            this.onCheckedChangeListener = onCheckedChangeListener
            return this
        }

        fun setFragment(fragment: LatteDelegate): Builder {
            this.fragment = fragment
            return this
        }

        fun build(): ListBean {
            return ListBean(itemType, imageUrl, text, value, id, fragment, onCheckedChangeListener)
        }
    }
}
