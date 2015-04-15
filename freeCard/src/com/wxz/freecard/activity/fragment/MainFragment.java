package com.wxz.freecard.activity.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.wxz.freecard.R;
import com.wxz.freecard.CardApplication.OnLocationReceivedListener;
import com.wxz.freecard.activity.CityActivity;
import com.wxz.freecard.activity.MemberCardActivity;
import com.wxz.freecard.activity.MsgCenterActivity;
import com.wxz.freecard.activity.SellerInfoActivity;
import com.wxz.freecard.adapter.MainFragmentListAdapter;
import com.wxz.freecard.adapter.PicPagerAdapter;
import com.wxz.freecard.bean.SellerInfo;
import com.wxz.freecard.utils.AdsPlayer;
import com.wxz.freecard.view.viewpagerindicator.CirclePageIndicator;

public class MainFragment extends BaseFragment
{
    @ViewInject(R.id.tv_city)
    private TextView tvLocation;
    @ViewInject(R.id.search_input)
    private TextView tvSearch;
    @ViewInject(R.id.tv_msg)
    private TextView tvMsg;
    @ViewInject(R.id.tv_msg_count)
    private TextView tvMsgCount;
    @ViewInject(R.id.list)
    private ListView list;
    @ViewInject(R.id.vp_ads)
    private ViewPager adsPager;
    @ViewInject(R.id.indicator)
    private CirclePageIndicator indicator;
    
    private View headerView;
    
    private PicPagerAdapter pagerAdapter;
    
    private MainFragmentListAdapter listAdapter;
    
    private AdsPlayer adsPlayer;

    private LocationClient mLocationClient;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ViewUtils.inject(this, view);
        headerView = inflater.inflate(R.layout.ads_view_pager, list,false);
        ViewUtils.inject(this, headerView);
        return view;
    }
    private void initLocationOptions()
    {
        mLocationClient = getMyApplication().mLocationClient;
        getMyApplication().addLocationReceivedListener(listener);
        mLocationClient.start();
    }
    
    private OnLocationReceivedListener listener = new OnLocationReceivedListener()
    {
        
        @Override
        public void onReceived()
        {
            new Handler().post(new Runnable()
            {
                
                @Override
                public void run()
                {
                    LogUtils.e("city:"+getMyApplication().getLocationInfo().city);
                    if (getMyApplication().getLocationInfo().setCity.equals(""))
                        tvLocation.setText(getMyApplication().getLocationInfo().city);
                }
            });
        }
    };
    
    @OnClick({R.id.tv_city,R.id.search_input,R.id.tv_msg_count,R.id.tv_msg})
    public void onClick(View v)
    {
        Intent intent;
        switch(v.getId())
        {
            case R.id.tv_city:
                LogUtils.i("city_click");
                intent = new Intent(getActivity(), CityActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.search_input:
                LogUtils.i("search_click");
                break;
            case R.id.tv_msg_count:
            case R.id.tv_msg:
                LogUtils.i("msg_click");
                intent = new Intent(getActivity(), MsgCenterActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }
    @OnItemClick(R.id.list)
    private void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    {
        int headerCount = list.getHeaderViewsCount();
        Intent intent = new Intent(getActivity(), SellerInfoActivity.class);
        SellerInfo info = (SellerInfo)listAdapter.getItem(position - headerCount);
        intent.putExtra("sellerinfo", info);
        startActivity(intent);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        initLocationOptions();
        setupViews();
        adsPlayer = new AdsPlayer(adsPager, new Handler());
        adsPlayer.play();
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 0:
                if (resultCode == Activity.RESULT_OK)
                {
                    String city = data.getStringExtra("city");
                    tvLocation.setText(city);
                }
                break;
            
            default:
                break;
        }
    }
    
    @Override
    public void onStart()
    {
        super.onStart();
        mLocationClient.requestLocation();
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
        mLocationClient.stop();
        mLocationClient = null;
        getMyApplication().removeLocationReceivedListener(listener);
    }
    
    private void setupViews()
    {
        if (getMyApplication().getLocationInfo().setCity.equals(""))
            tvLocation.setText(getMyApplication().getLocationInfo().city);
        else
            tvLocation.setText(getMyApplication().getLocationInfo().setCity);
        
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
