package com.yuejianzhong.latte_core.ui.banner

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator

class HolderCreator : CBViewHolderCreator<ImageHolder> {
    override fun createHolder(): ImageHolder {
        return ImageHolder()
    }

}