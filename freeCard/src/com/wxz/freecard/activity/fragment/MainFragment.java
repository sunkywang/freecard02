package com.wxz.freecard.activity.fragment;

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
import com.wxz.freecard.view.viewpagerindicator.UnderlinePageIndicator;

public class MainFragment extends BaseFragment
{
    @ViewInject(R.id.list)
    private ListView list;
    @ViewInject(R.id.vp_ads)
    private ViewPager vpAds;
    @ViewInject(R.id.indicator)
    private UnderlinePageIndicator indicator;
    private View headerView;
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
        list.addHeaderView(headerView);
        list.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), true, true));
        indicator.setViewPager(vpAds);
    }
    
}
