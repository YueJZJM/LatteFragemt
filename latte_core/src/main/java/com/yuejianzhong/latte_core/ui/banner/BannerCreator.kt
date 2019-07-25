package com.yuejianzhong.latte_core.ui.banner

import android.widget.AdapterView
import com.ToxicBakery.viewpager.transforms.DefaultTransformer
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator
import com.bigkoo.convenientbanner.holder.Holder
import com.bigkoo.convenientbanner.listener.OnItemClickListener
import com.yuejianzhong.latte_core.R

class BannerCreator {
    companion object{
        public fun setDefault(convenientBanner: ConvenientBanner<String>,
                              banners:ArrayList<String>,
                              onItemClickListener: OnItemClickListener){
            convenientBanner.setPages(HolderCreator(),banners)
                    .setPageIndicator(intArrayOf(R.drawable.dot_normal,R.drawable.dot_focus))
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .setOnItemClickListener(onItemClickListener)
                    .setPageTransformer(DefaultTransformer())
                    .isCanLoop = true


        }
    }

}
