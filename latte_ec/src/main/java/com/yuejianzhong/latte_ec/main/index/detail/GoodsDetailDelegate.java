package com.yuejianzhong.latte_ec.main.index.detail;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.YoYo;
import com.joanzapata.iconify.widget.IconTextView;
import com.yuejianzhong.latte_core.delegate.LatteDelegate;
import com.yuejianzhong.latte_core.net.RestClient;
import com.yuejianzhong.latte_core.net.callback.ISuccess;
import com.yuejianzhong.latte_core.ui.banner.HolderCreator;
import com.yuejianzhong.latte_core.ui.widget.CircleTextView;
import com.yuejianzhong.latte_core.util.animation.BezierAnimation;
import com.yuejianzhong.latte_core.util.animation.BezierUtil;
import com.yuejianzhong.latte_ec.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;

/**
 * Created by Anding on 2019/2/13 21:42
 * Note:  商品详情页
 */

public class GoodsDetailDelegate extends LatteDelegate implements AppBarLayout.OnOffsetChangedListener,
        BezierUtil.AnimationListener{

    private TabLayout mTabLayout = null;
    private ViewPager mViewPager = null;
    private ConvenientBanner<String> mBanner = null;

    private CircleTextView mCircleTextView = null;
    private RelativeLayout mRlAddShopCart = null;
    private IconTextView mIconShopCart = null;

    public static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;

    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate()
            .override(100, 100);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
        }
    }

    public static GoodsDetailDelegate create(int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate fragment = new GoodsDetailDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @NotNull
    @Override
    public Object setLayout() {
        return R.layout.delagate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {

        mTabLayout = $(R.id.tab_layout);
        mViewPager = $(R.id.view_pager);
        mBanner = $(R.id.detail_banner);

        final CollapsingToolbarLayout mCollapsingToolbarLayout = $(R.id.collapsing_toolbar_detail);
        final AppBarLayout mAppBar = $(R.id.app_bar_detail);

        //底部
        mCircleTextView = $(R.id.tv_shopping_cart_amount);
        mRlAddShopCart = $(R.id.rl_add_shop_cart);
        mIconShopCart = $(R.id.icon_shop_cart);
        $(R.id.goods_detail_toolbar).setPadding(0, getStatusBarHeight(), 0, 0);
        $(R.id.rl_add_shop_cart).setOnClickListener(view -> onClickAddShopCart());
        $(R.id.icon_goods_back).setOnClickListener(v -> getSupportDelegate().pop());

        mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        mAppBar.addOnOffsetChangedListener(this);
        mCircleTextView.setCircleBackground(Color.RED);
        initData();
        initTabLayout();
    }

    private void onClickAddShopCart() {
        final CircleImageView animImg = new CircleImageView(getContext());
        Glide.with(this)
                .load(mGoodsThumbUrl)
                .apply(OPTIONS)
                .into(animImg);
        BezierAnimation.addCart(this, mRlAddShopCart, mIconShopCart, animImg, this);
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        final Context context = getContext();
        if (context != null) {
            mTabLayout.setSelectedTabIndicatorColor
                    (ContextCompat.getColor(context, R.color.app_main));
        }
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initData() {
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/goods_detail_data_1.json")
                .params("goods_id", String.valueOf(mGoodsId))
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtils.json("商品详情", response);
                        final JSONObject data =
                                JSON.parseObject(response).getJSONObject("data");
                        initBanner(data);
                        initGoodsInfo(data);
                        initPager(data);
                        setShopCartCount(data);
                    }
                })
                .build()
                .get();

    }

    private void initBanner(JSONObject data) {
        final JSONArray array = data.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            images.add(array.getString(i));
        }
        mBanner
                .setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);

    }

    private void initGoodsInfo(JSONObject data) {
        final String goodsData = data.toJSONString();
        getSupportDelegate().
                loadRootFragment(R.id.frame_goods_info, GoodsInfoDelegate.create(goodsData));
    }

    private void initPager(JSONObject data) {
        assert getFragmentManager() != null;
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }

    private void setShopCartCount(JSONObject data) {
        mGoodsThumbUrl = data.getString("thumb");
        if (mShopCount == 0) {
            mCircleTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationEnd() {
        YoYo.with(new ScaleUpAnimator())
                .duration(500)
                .playOn(mIconShopCart);
        RestClient.builder()
                .url("http://mock.fulingjie.com/mock-android/data/add_shop_cart.json")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtils.json("ADD", response);
                        final boolean isAdded = JSON.parseObject(response).getBoolean("data");
                        if (isAdded) {
                            mShopCount++;
                            mCircleTextView.setVisibility(View.VISIBLE);
                            mCircleTextView.setText(String.valueOf(mShopCount));
                        }
                    }
                })
                .params("count", String.valueOf(mShopCount))
                .build()
                .post();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

    }
}
