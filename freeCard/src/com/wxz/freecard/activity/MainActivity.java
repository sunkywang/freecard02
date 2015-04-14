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
    
    @ViewInject(R.id.tab_bottom)
    private RadioGroup tabGroup;
    
    private MainFragment mainFragment;
    private CardBagFragment cardBagFragment;
    private DiscoveryFragment discoveryFragment;
    private MineFragment mineFragment;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewUtils.inject(this);
        addMainFragment();
    }
    
    private void addMainFragment()
    {
        mainFragment = new MainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, mainFragment,MainFragment.class.getSimpleName());
        ft.commitAllowingStateLoss();
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
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        
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
                }
                safeHideFragment(ft, mainFragment);
                safeHideFragment(ft, discoveryFragment);
                safeHideFragment(ft, mineFragment);
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
                }
                safeHideFragment(ft, cardBagFragment);
                safeHideFragment(ft, mainFragment);
                safeHideFragment(ft, mineFragment);
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
                safeHideFragment(ft, cardBagFragment);
                safeHideFragment(ft, mainFragment);
                safeHideFragment(ft, discoveryFragment);

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
