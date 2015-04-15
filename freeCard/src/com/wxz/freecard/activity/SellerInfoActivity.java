package com.wxz.freecard.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wxz.freecard.R;
import com.wxz.freecard.bean.SellerInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SellerInfoActivity extends BaseActivity
{
    @ViewInject(R.id.title)
    private TextView tvTitle;
    
    @ViewInject(R.id.iv_back)
    private ImageView ivBack;
    
    private SellerInfo sellerInfo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_info);
        ViewUtils.inject(this);
        setupView();
    }
    private void setupView()
    {
        sellerInfo = (SellerInfo)getIntent().getSerializableExtra("sellerinfo");
        tvTitle.setText(sellerInfo.name);
    }
    
    @OnClick({R.id.iv_back})
    private void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                break;
            
            default:
                break;
        }
    }
}
