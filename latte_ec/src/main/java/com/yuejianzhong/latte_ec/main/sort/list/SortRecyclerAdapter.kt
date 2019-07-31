package com.yuejianzhong.latte_ec.main.sort.list
import com.yuejianzhong.latte_ec.main.sort.content.ContentDelegate
import com.yuejianzhong.latte_core.ui.recycler.MultipleFields
import com.yuejianzhong.latte_core.ui.recycler.ItemType
import com.yuejianzhong.latte_core.ui.recycler.MultipleItemEntity
import com.yuejianzhong.latte_core.ui.recycler.MultipleViewHolder
import com.yuejianzhong.latte_ec.main.sort.SortDelegate
import com.yuejianzhong.latte_core.ui.recycler.MultipleRecyclerAdapter


import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.view.View
import com.yuejianzhong.latte_ec.R


import me.yokeyword.fragmentation.SupportHelper

/**
 * Created by 傅令杰
 */

class SortRecyclerAdapter constructor(data: List<MultipleItemEntity>, private val DELEGATE: SortDelegate) : MultipleRecyclerAdapter(data) {
    private var mPrePosition = 0

    init {
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list)
    }

    override fun convert(holder: MultipleViewHolder?, entity: MultipleItemEntity?) {
        super.convert(holder, entity)
        when (holder!!.itemViewType) {
            ItemType.VERTICAL_MENU_LIST -> {
                val text:String = entity!!.getField(MultipleFields.TEXT) as String
                val isClicked:Boolean = entity.getField(MultipleFields.TAG)!! as Boolean
                val name = holder.getView<AppCompatTextView>(R.id.tv_vertical_item_name)
                val line = holder.getView<View>(R.id.view_line)
                val itemView = holder.itemView
                itemView.setOnClickListener {
                    val currentPosition = holder.adapterPosition
                    if (mPrePosition != currentPosition) {
                        //还原上一个
                        data[mPrePosition].setField(MultipleFields.TAG, false)
                        notifyItemChanged(mPrePosition)

                        //更新选中的item
                        entity.setField(MultipleFields.TAG, true)
                        notifyItemChanged(currentPosition)
                        mPrePosition = currentPosition

                        val contentId = data[currentPosition].getField(MultipleFields.ID)!! as Int
                        showContent(contentId!!)
                    }
                }

                if (!isClicked) {
                    line.visibility = View.INVISIBLE
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black))
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background))
                } else {
                    line.visibility = View.VISIBLE
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main))
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main))
                    itemView.setBackgroundColor(Color.WHITE)
                }

                holder?.setText(R.id.tv_vertical_item_name, text)
            }
            else -> {
            }
        }
    }

    private fun showContent(contentId: Int) {
        val delegate = ContentDelegate.newInstance(contentId)
        switchContent(delegate)
    }

    private fun switchContent(delegate: ContentDelegate) {
        val contentDelegate = SupportHelper.findFragment(DELEGATE.childFragmentManager, ContentDelegate::class.java)
        contentDelegate?.supportDelegate?.replaceFragment(delegate, false)
    }
}
