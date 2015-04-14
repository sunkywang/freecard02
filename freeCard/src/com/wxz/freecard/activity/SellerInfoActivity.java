package com.wxz.freecard.activity;

import com.lidroid.xutils.ViewUtils;
import com.wxz.freecard.R;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SellerInfoActivity extends BaseActivity
{
    
    private TextView tvTitle;
    
    private ImageView ivBack;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_info);
        ViewUtils.inject(this);
    }
}
