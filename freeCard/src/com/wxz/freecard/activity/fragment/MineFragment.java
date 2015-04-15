package com.wxz.freecard.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wxz.freecard.R;
import com.wxz.freecard.activity.SettingsActivity;

public class MineFragment extends BaseFragment
{
    @ViewInject(R.id.title)
    private TextView tvTitle;
    @ViewInject(R.id.tv_right)
    private TextView tvRightText;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        ViewUtils.inject(this, view);
        setupView();
        return view;
    }
    
    private void setupView()
    {
        tvTitle.setText("我的");
        tvRightText.setText("设置");
        tvRightText.setVisibility(View.VISIBLE);
    }
    
    @OnClick({R.id.tv_right})
    private void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tv_right:
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
            
            default:
                break;
        }
    }
    
}
