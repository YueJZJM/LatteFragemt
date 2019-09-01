package com.yuejianzhong.latte_ec.main.index.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.alibaba.fastjson.JSONObject


class TabPagerAdapter(fm: FragmentManager, data: JSONObject) : FragmentStatePagerAdapter(fm) {

    private val TAB_TITLES = ArrayList<String>()
    private val PICTURES = ArrayList<ArrayList<String>>()

    init {
        //获取tabs信息，注意，这里的tabs是一条信息
        val tabs = data.getJSONArray("tabs")
        val size = tabs.size
        for (i in 0 until size) {
            val eachTab = tabs.getJSONObject(i)
            val name = eachTab.getString("name")
            val pictureUrls = eachTab.getJSONArray("pictures")
            val eachTabPicturesArray = ArrayList<String>()
            //存储每个图片
            val pictureSize = pictureUrls.size
            for (j in 0 until pictureSize) {
                eachTabPicturesArray.add(pictureUrls.getString(j))
            }
            TAB_TITLES.add(name)
            PICTURES.add(eachTabPicturesArray)
        }
    }

    override fun getItem(position: Int): Fragment? {
        if (position == 0) {
            return ImageDelegate.create(PICTURES[0])
        } else if (position == 1) {
            return ImageDelegate.create(PICTURES[1])
        }
        return null
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }
}
