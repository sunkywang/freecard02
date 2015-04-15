package com.wxz.freecard.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wxz.freecard.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MemberCardActivity extends BaseActivity
{
    
    @ViewInject(R.id.title)
    private TextView tvTitle;
    @ViewInject(R.id.iv_back)
    private ImageView ivBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_card_activity);
        ViewUtils.inject(this);
        setupView();
    }
    
    private void setupView()
    {
        tvTitle.setText("会员卡");
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
