package com.wxz.freecard.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.wxz.freecard.R;

public class CardBagFragment extends BaseFragment
{
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ViewUtils.inject(this, view);
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
    
}
