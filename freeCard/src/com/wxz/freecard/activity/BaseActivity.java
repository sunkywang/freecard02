package com.wxz.freecard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.lidroid.xutils.util.LogUtils;

public class BaseActivity extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        LogUtils.i("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart()
    {
        LogUtils.i("onStart");
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        LogUtils.i("onResume");
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        LogUtils.i("onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause()
    {
        LogUtils.i("onPause");
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        LogUtils.i("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        LogUtils.i("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        LogUtils.i("onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onBackPressed()
    {
        LogUtils.i("onBackPressed");
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        LogUtils.i("onActivityResult,requestCode:"+requestCode+" resultCode:"+resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }
    
}
