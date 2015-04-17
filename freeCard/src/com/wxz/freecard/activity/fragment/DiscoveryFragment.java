package com.wxz.freecard.activity.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wxz.freecard.R;
import com.wxz.freecard.adapter.PicPagerAdapter;
import com.wxz.freecard.bean.SellerInfo;
import com.wxz.freecard.constant.Samples;
import com.wxz.freecard.utils.AdsPlayer;
import com.wxz.freecard.view.viewpagerindicator.CirclePageIndicator;

public class DiscoveryFragment extends BaseFragment
{
    @ViewInject(R.id.title)
    private TextView tvTitle;
    @ViewInject(R.id.vp_ads)
    private ViewPager adsPager;
    @ViewInject(R.id.indicator)
    private CirclePageIndicator indicator;
    @ViewInject(R.id.tv_category_01)
    private TextView tvCategory01;
    @ViewInject(R.id.tv_category_02)
    private TextView tvCategory02;
    @ViewInject(R.id.tv_category_03)
    private TextView tvCategory03;
    @ViewInject(R.id.tv_category_04)
    private TextView tvCategory04;
    @ViewInject(R.id.tv_category_05)
    private TextView tvCategory05;
    @ViewInject(R.id.tv_category_06)
    private TextView tvCategory06;
    @ViewInject(R.id.tv_category_07)
    private TextView tvCategory07;
    @ViewInject(R.id.tv_category_08)
    private TextView tvCategory08;
    @ViewInject(R.id.layout_near_grid01)
    private FrameLayout layoutNearGrid01;
    @ViewInject(R.id.layout_near_grid02)
    private FrameLayout layoutNearGrid02;
    @ViewInject(R.id.layout_near_grid03)
    private FrameLayout layoutNearGrid03;
    @ViewInject(R.id.layout_near_grid04)
    private FrameLayout layoutNearGrid04;
    @ViewInject(R.id.iv_near_grid01)
    private ImageView ivNearGrid01;
    @ViewInject(R.id.iv_near_grid02)
    private ImageView ivNearGrid02;
    @ViewInject(R.id.iv_near_grid03)
    private ImageView ivNearGrid03;
    @ViewInject(R.id.iv_near_grid04)
    private ImageView ivNearGrid04;
    @ViewInject(R.id.tv_near_grid01)
    private TextView tvNearGrid01;
    @ViewInject(R.id.tv_near_grid02)
    private TextView tvNearGrid02;
    @ViewInject(R.id.tv_near_grid03)
    private TextView tvNearGrid03;
    @ViewInject(R.id.tv_near_grid04)
    private TextView tvNearGrid04;
    @ViewInject(R.id.layout_recommend_grid01)
    private FrameLayout layoutRecommendGrid01;
    @ViewInject(R.id.layout_recommend_grid02)
    private FrameLayout layoutRecommendGrid02;
    @ViewInject(R.id.layout_recommend_grid03)
    private FrameLayout layoutRecommendGrid03;
    @ViewInject(R.id.layout_recommend_grid04)
    private FrameLayout layoutRecommendGrid04;
    @ViewInject(R.id.iv_recommend_grid01)
    private ImageView ivRecommendGrid01;
    @ViewInject(R.id.iv_recommend_grid02)
    private ImageView ivRecommendGrid02;
    @ViewInject(R.id.iv_recommend_grid03)
    private ImageView ivRecommendGrid03;
    @ViewInject(R.id.iv_recommend_grid04)
    private ImageView ivRecommendGrid04;
    @ViewInject(R.id.tv_recommend_grid01)
    private TextView tvRecommendGrid01;
    @ViewInject(R.id.tv_recommend_grid02)
    private TextView tvRecommendGrid02;
    @ViewInject(R.id.tv_recommend_grid03)
    private TextView tvRecommendGrid03;
    @ViewInject(R.id.tv_recommend_grid04)
    private TextView tvRecommendGrid04;
    
    private PicPagerAdapter pagerAdapter;
    private AdsPlayer adsPlayer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.discovery_fragment, container, false);
        ViewUtils.inject(this, view);
        setupView();
        return view;
    }
    
    private void setupView()
    {
        tvTitle.setText("发现");
        pagerAdapter = new PicPagerAdapter(getSellerInfos());
        adsPager.setAdapter(pagerAdapter);
        indicator.setViewPager(adsPager);
        adsPlayer = new AdsPlayer(adsPager, new Handler());
        adsPlayer.play();
        ImageLoader.getInstance().displayImage(Samples.pics[0], ivNearGrid01);
        ImageLoader.getInstance().displayImage(Samples.pics[1], ivNearGrid02);
        ImageLoader.getInstance().displayImage(Samples.pics[2], ivNearGrid03);
        ImageLoader.getInstance().displayImage(Samples.pics[3], ivNearGrid04);
        ImageLoader.getInstance().displayImage(Samples.pics[4], ivRecommendGrid01);
        ImageLoader.getInstance().displayImage(Samples.pics[5], ivRecommendGrid02);
        ImageLoader.getInstance().displayImage(Samples.pics[6], ivRecommendGrid03);
        ImageLoader.getInstance().displayImage(Samples.pics[7], ivRecommendGrid04);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        adsPlayer.resume();
    }
    
    @Override
    public void onPause()
    {
        super.onPause();
        adsPlayer.pause();
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        adsPlayer.release();
    }
    
    private List<SellerInfo> getSellerInfos()
    {
        List<SellerInfo> list = new ArrayList<SellerInfo>();
        SellerInfo info1 = new SellerInfo();
        info1.name = "商家1";
        info1.detail = "办卡全面8折，赶快行动！";
        info1.pic_url = "http://img0.imgtn.bdimg.com/it/u=568515214,1152612734&fm=116&gp=0.jpg";
        list.add(info1);
        SellerInfo info2 = new SellerInfo();
        info2.name = "商家3";
        info2.detail = "来就送你钱，统统不要钱，就是这么任性！";
        info2.pic_url = "http://img2.imgtn.bdimg.com/it/u=3452729668,1362003854&fm=116&gp=0.jpg";
        list.add(info2);
        SellerInfo info3 = new SellerInfo();
        info3.name = "商家4";
        info3.detail = "办卡全面8折，赶快行动！";
        info3.pic_url = "http://img0.imgtn.bdimg.com/it/u=4266094203,516749329&fm=116&gp=0.jpg";
        list.add(info3);
        return list;
    }
}
