package com.wxz.freecard.activity.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wxz.freecard.R;
import com.wxz.freecard.adapter.PicPagerAdapter;
import com.wxz.freecard.bean.SellerInfo;
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
