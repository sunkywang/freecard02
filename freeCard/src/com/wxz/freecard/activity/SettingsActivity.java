package com.wxz.freecard.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wxz.freecard.R;

public class SettingsActivity extends BaseActivity
{
    @ViewInject(R.id.title)
    private TextView tvTitle;
    @ViewInject(R.id.iv_back)
    private ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        ViewUtils.inject(this);
        setupView();
    }

    private void setupView()
    {
        tvTitle.setText("设置");
    }
    
    @OnClick({R.id.iv_back})
    private void onClick(View v)
    {
        if (v.getId() == R.id.iv_back)
        {
            finish();
        }
    }
}
