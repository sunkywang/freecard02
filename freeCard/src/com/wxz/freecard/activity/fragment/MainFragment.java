package com.wxz.freecard.activity.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.wxz.freecard.R;
import com.wxz.freecard.adapter.MainFragmentListAdapter;
import com.wxz.freecard.adapter.PicPagerAdapter;
import com.wxz.freecard.bean.SellerInfo;
import com.wxz.freecard.view.viewpagerindicator.CirclePageIndicator;
import com.wxz.freecard.view.viewpagerindicator.UnderlinePageIndicator;

public class MainFragment extends BaseFragment
{
    @ViewInject(R.id.list)
    private ListView list;
    @ViewInject(R.id.vp_ads)
    private ViewPager adsPager;
    @ViewInject(R.id.indicator)
    private CirclePageIndicator indicator;
    
    private View headerView;
    
    private PicPagerAdapter pagerAdapter;
    
    private MainFragmentListAdapter listAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ViewUtils.inject(this, view);
        headerView = inflater.inflate(R.layout.ads_view_pager, list,false);
        ViewUtils.inject(this, headerView);
        setupViews();
        return view;
    }
    
    private void setupViews()
    {
    	pagerAdapter = new PicPagerAdapter(getSellerInfos());
    	adsPager.setAdapter(pagerAdapter);
    	indicator.setViewPager(adsPager);
        list.addHeaderView(headerView);
        listAdapter = new MainFragmentListAdapter(getActivity(), getSellerInfos());
        list.setAdapter(listAdapter);
        list.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, true));
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
