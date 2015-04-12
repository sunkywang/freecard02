package com.wxz.freecard.activity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wxz.freecard.R;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MsgCenterActivity extends BaseActivity{

	@ViewInject(R.id.iv_back)
	private ImageView ivBack;
	@ViewInject(R.id.title)
	private TextView titleTextView;
	@ViewInject(R.id.msg_tab_group)
	private RadioGroup msgTabGroup;
	@ViewInject(R.id.list)
	private ListView list;
	@ViewInject(R.id.tv_empty_text)
	private TextView emptyView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg_center);
		ViewUtils.inject(this);
		setupView();
	}
	
	private void setupView()
	{
		
	}
	
}
