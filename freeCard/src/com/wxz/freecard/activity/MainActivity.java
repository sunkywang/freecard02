package com.wxz.freecard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.wxz.freecard.CardApplication;
import com.wxz.freecard.CardApplication.OnLocationReceivedListener;
import com.wxz.freecard.R;
import com.wxz.freecard.activity.fragment.CardBagFragment;
import com.wxz.freecard.activity.fragment.DiscoveryFragment;
import com.wxz.freecard.activity.fragment.MainFragment;
import com.wxz.freecard.activity.fragment.MineFragment;
import com.wxz.freecard.utils.ToastUtil;

public class MainActivity extends BaseActivity
{
    @ViewInject(R.id.tv_city)
    private TextView tvLocation;
    @ViewInject(R.id.search_input)
    private TextView tvSearch;
    @ViewInject(R.id.tv_msg)
    private TextView tvMsg;
    @ViewInject(R.id.tv_msg_count)
    private TextView tvMsgCount;
    @ViewInject(R.id.tab_bottom)
    private RadioGroup tabGroup;
    
    private MainFragment mainFragment;
    private CardBagFragment cardBagFragment;
    private DiscoveryFragment discoveryFragment;
    private MineFragment mineFragment;
    
    private LocationClient mLocationClient;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewUtils.inject(this);
        addMainFragment();
        initLocationOptions();
        setupViews();
    }
    
    private void setupViews()
    {
        if (getMyApplication().getLocationInfo().setCity.equals(""))
            tvLocation.setText(getMyApplication().getLocationInfo().city);
        else
            tvLocation.setText(getMyApplication().getLocationInfo().setCity);
    }
    
    private void addMainFragment()
    {
        mainFragment = new MainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, mainFragment,MainFragment.class.getSimpleName());
        ft.commitAllowingStateLoss();
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
    
    @Override
    protected void onStart()
    {
        super.onStart();
        mLocationClient.requestLocation();
    }
    
    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mLocationClient.stop();
        mLocationClient = null;
        getMyApplication().removeLocationReceivedListener(listener);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 0:
                if (resultCode == RESULT_OK)
                {
                    String city = data.getStringExtra("city");
                    tvLocation.setText(city);
                }
                break;
            
            default:
                break;
        }
    }
    
    @OnClick({R.id.tv_city,R.id.search_input,R.id.tv_msg_count,R.id.tv_msg})
    public void onClick(View v)
    {
        Intent intent;
        switch(v.getId())
        {
            case R.id.tv_city:
                LogUtils.i("city_click");
                intent = new Intent(this, CityActivity.class);
                startActivityForResult(intent,0);
                break;
            case R.id.search_input:
                LogUtils.i("search_click");
                break;
            case R.id.tv_msg_count:
            case R.id.tv_msg:
                LogUtils.i("msg_click");
                intent = new Intent(this, MsgCenterActivity.class);
                startActivityForResult(intent,1);
                break;
        }
    }
    @OnRadioGroupCheckedChange(R.id.tab_bottom)
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch(checkedId)
        {
            case R.id.main_tab:
                ft.show(mainFragment);
                safeHideFragment(ft, cardBagFragment);
                safeHideFragment(ft, discoveryFragment);
                safeHideFragment(ft, mineFragment);
                break;
            case R.id.cardbag_tab:
                if (cardBagFragment == null)
                {
                    cardBagFragment = new CardBagFragment();
                    ft.add(R.id.container, cardBagFragment,CardBagFragment.class.getSimpleName());
                }
                else
                {
                    ft.show(cardBagFragment);
                    safeHideFragment(ft, mainFragment);
                    safeHideFragment(ft, discoveryFragment);
                    safeHideFragment(ft, mineFragment);
                }
                break;
            case R.id.discovery_tab:
                if (discoveryFragment == null)
                {
                    discoveryFragment = new DiscoveryFragment();
                    ft.add(R.id.container, discoveryFragment,DiscoveryFragment.class.getSimpleName());
                }
                else
                {
                    ft.show(discoveryFragment);
                    safeHideFragment(ft, cardBagFragment);
                    safeHideFragment(ft, mainFragment);
                    safeHideFragment(ft, mineFragment);
                }
                break;
            case R.id.mine_tab:
                if (mineFragment == null)
                {
                    mineFragment = new MineFragment();
                    ft.add(R.id.container, mineFragment,MineFragment.class.getSimpleName());
                }
                else
                {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }
    
    private void safeHideFragment(FragmentTransaction ft, Fragment fragment)
    {
        if (fragment != null && fragment.isVisible())
        {
            ft.hide(fragment);
        }
    }
    
    boolean exitFlag;
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (exitFlag)
            {
                return super.onKeyDown(keyCode, event);
            }
            else
            {
                exitFlag = true;
                ToastUtil.showShort(this, "再按一次退出");
                new Handler().postDelayed(new Runnable()
                {
                    
                    @Override
                    public void run()
                    {
                        exitFlag = false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    
}
