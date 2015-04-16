package com.wxz.freecard.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wxz.freecard.R;
import com.wxz.freecard.adapter.BusinessSaleListAdapter;
import com.wxz.freecard.bean.MessageInfo;
import com.wxz.freecard.bean.SellerInfo;

public class BusinessOnSaleListActivity extends BaseActivity
{
    @ViewInject(R.id.title)
    private TextView tvTitle;
    
    @ViewInject(R.id.iv_back)
    private ImageView ivBack;
    
    @ViewInject(R.id.list)
    private ListView list;
    
    private BusinessSaleListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_salelist_activity);
        ViewUtils.inject(this);
        setupView();
    }
    private void setupView()
    {
        tvTitle.setText("商家优惠");
        List<MessageInfo> infos = new ArrayList<MessageInfo>();
        infos.add(new MessageInfo());
        infos.add(new MessageInfo());
        infos.add(new MessageInfo());
        infos.add(new MessageInfo());
        infos.add(new MessageInfo());
        infos.add(new MessageInfo());
        infos.add(new MessageInfo());
        mAdapter = new BusinessSaleListAdapter(this, infos);
        list.setAdapter(mAdapter);
        
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
