package com.wxz.freecard.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.wxz.freecard.R;
import com.wxz.freecard.adapter.MsgListAdapter;
import com.wxz.freecard.bean.MessageInfo;

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
	
	private List<MessageInfo> userMsgList;
    private List<MessageInfo> systemMsgList;
    private List<MessageInfo> sellerMsgList;
    private MsgListAdapter userListAdapter;
    private MsgListAdapter systemListAdapter;
    private MsgListAdapter sellerListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.msg_center);
		ViewUtils.inject(this);
		setupView();
	}
	
	private void setupView()
	{
	    titleTextView.setText("消息中心");
		makeupList();
		list.setAdapter(userListAdapter);
	}
	
	private void makeupList()
	{
	    userMsgList = new ArrayList<MessageInfo>();
        systemMsgList = new ArrayList<MessageInfo>();
        sellerMsgList = new ArrayList<MessageInfo>();
        String userMsgjson = "["
            + "{id:1,type:1,title:\"用户消息1\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-01\",isRead:false},"
            + "{id:2,type:1,title:\"用户消息2\",content:\"您有一条消息oooooooo\",msg_pic:\"\",msg_date:\"2015-04-03\",isRead:false},"
            + "{id:3,type:1,title:\"用户消息3\",content:\"您有一条消息aaaaaa\",msg_pic:\"\",msg_date:\"2015-04-04\",isRead:false},"
            + "{id:4,type:1,title:\"用户消息4\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-05\",isRead:false},"
            + "{id:5,type:1,title:\"用户消息5\",content:\"您有一条消息aaaa\",msg_pic:\"\",msg_date:\"2015-04-06\",isRead:false},"
            + "{id:6,type:1,title:\"用户消息6\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-08\",isRead:false}"
            + "]";
        String systemMsgjson = "["
            + "{id:1,type:2,title:\"系统消息1\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-01\",isRead:false},"
            + "{id:2,type:2,title:\"系统消息2\",content:\"您有一条消息oooooooo\",msg_pic:\"\",msg_date:\"2015-04-03\",isRead:false},"
            + "{id:3,type:2,title:\"系统消息3\",content:\"您有一条消息aaaaaa\",msg_pic:\"\",msg_date:\"2015-04-04\",isRead:false},"
            + "{id:4,type:2,title:\"系统消息4\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-05\",isRead:false},"
            + "{id:5,type:2,title:\"系统消息5\",content:\"您有一条消息aaaa\",msg_pic:\"\",msg_date:\"2015-04-06\",isRead:false},"
            + "{id:6,type:2,title:\"系统消息6\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-08\",isRead:false}"
            + "]";
        String sellerMsgjson = "["
            + "{id:1,type:3,title:\"黄焖鸡米饭\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-01\",isRead:false},"
            + "{id:2,type:3,title:\"海阔网吧\",content:\"您有一条消息oooooooo\",msg_pic:\"\",msg_date:\"2015-04-03\",isRead:false},"
            + "{id:3,type:3,title:\"天上人间\",content:\"您有一条消息aaaaaa\",msg_pic:\"\",msg_date:\"2015-04-04\",isRead:false},"
            + "{id:4,type:3,title:\"金陵饭店\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-05\",isRead:false},"
            + "{id:5,type:3,title:\"夫子庙小吃\",content:\"您有一条消息aaaa\",msg_pic:\"\",msg_date:\"2015-04-06\",isRead:false},"
            + "{id:6,type:3,title:\"新百商城\",content:\"您有一条消息\",msg_pic:\"\",msg_date:\"2015-04-08\",isRead:false}"
            + "]";
        Gson gson = new Gson();
        userMsgList = gson.fromJson(userMsgjson, new TypeToken<ArrayList<MessageInfo>>(){}.getType());
        systemMsgList = gson.fromJson(systemMsgjson, new TypeToken<ArrayList<MessageInfo>>(){}.getType());
        sellerMsgList = gson.fromJson(sellerMsgjson, new TypeToken<ArrayList<MessageInfo>>(){}.getType());
        userListAdapter = new MsgListAdapter(this, userMsgList);
        systemListAdapter = new MsgListAdapter(this, systemMsgList);
        sellerListAdapter = new MsgListAdapter(this, sellerMsgList);
	}
	
	@OnClick(R.id.iv_back)
	private void onClick(View v)
	{
	    finish();
	}
	
	@OnItemClick(R.id.list)
	private void onListItemClick(AdapterView<?> parent, View view, int position, long id)
	{
	    switch (msgTabGroup.getCheckedRadioButtonId())
        {
            case R.id.user_tab:
                ((MessageInfo)userListAdapter.getItem(position)).isRead = true;
                userListAdapter.notifyDataSetChanged();
                break;
            case R.id.system_tab:
                ((MessageInfo)systemListAdapter.getItem(position)).isRead = true;
                systemListAdapter.notifyDataSetChanged();
                break;
            case R.id.seller_tab:
                ((MessageInfo)sellerListAdapter.getItem(position)).isRead = true;
                sellerListAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
	}
	
	@OnRadioGroupCheckedChange(R.id.msg_tab_group)
	private void onCheckChange(RadioGroup group, int checkedId)
	{
	    list.setScrollY(0);
	    switch (checkedId)
        {
            case R.id.user_tab:
                list.setAdapter(userListAdapter);
                break;
            case R.id.system_tab:
                list.setAdapter(systemListAdapter);
                break;
            case R.id.seller_tab:
                list.setAdapter(sellerListAdapter);
                break;
            default:
                break;
        }
	}
	
}
