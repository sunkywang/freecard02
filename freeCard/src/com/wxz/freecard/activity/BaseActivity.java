package com.wxz.freecard.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

import com.lidroid.xutils.util.LogUtils;
import com.wxz.freecard.CardApplication;
import com.wxz.freecard.R;

public class BaseActivity extends FragmentActivity
{
    View progressbar;

    public CardApplication getMyApplication()
    {
        return (CardApplication)getApplication();
    }
    
    public void showProgress()
    {
        if (progressbar != null && progressbar.getParent()!=null)
        {
            return;
        }
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        if (progressbar == null)
        {
            progressbar = LayoutInflater.from(this).inflate(R.layout.custom_progressbar,null);
        }
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT);
        wm.addView(progressbar, params);
    }
    
    public void hideProgress()
    {
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        if (progressbar != null && progressbar.getParent() != null)
        {
            wm.removeView(progressbar);
        }
    }
    
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
        hideProgress();
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
