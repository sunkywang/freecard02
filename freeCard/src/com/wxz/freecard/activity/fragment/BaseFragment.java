package com.wxz.freecard.activity.fragment;

import com.lidroid.xutils.util.LogUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment
{

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        LogUtils.i("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        LogUtils.i("onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttach(Activity activity)
    {
        LogUtils.i("onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        LogUtils.i("onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LogUtils.i("onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy()
    {
        LogUtils.i("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView()
    {
        LogUtils.i("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach()
    {
        LogUtils.i("onDetach");
        super.onDetach();
    }

    @Override
    public void onPause()
    {
        LogUtils.i("onPause");
        super.onPause();
    }

    @Override
    public void onResume()
    {
        LogUtils.i("onResume");
        super.onResume();
    }

    @Override
    public void onStart()
    {
        LogUtils.i("onStart");
        super.onStart();
    }

    @Override
    public void onStop()
    {
        LogUtils.i("onStop");
        super.onStop();
    }
    
}
