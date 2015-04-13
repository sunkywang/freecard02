package com.wxz.freecard.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wxz.freecard.R;
import com.wxz.freecard.bean.MessageInfo;
import com.wxz.freecard.bean.SellerInfo;

/**
 * @author Administrator
 * 
 */
public class MsgListAdapter extends BasicListAdapter<MessageInfo>
{
    public MsgListAdapter(Context context, List<MessageInfo> list)
    {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
    	MessageInfo info = list.get(position);
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.msg_item, parent, false);
            holder = new ViewHolder();
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        boolean isRead = info.isRead;
        holder.msgTitle.setEnabled(!isRead);
        holder.msgContent.setEnabled(!isRead);
        holder.msgDate.setEnabled(!isRead);
        if (!isRead)
        {
            holder.msgTitle.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgContent.setTextColor(context.getResources().getColor(R.color.black));
            holder.msgDate.setTextColor(context.getResources().getColor(R.color.black));
        }
        else
        {
            holder.msgTitle.setTextColor(context.getResources().getColor(R.color.gray));
            holder.msgContent.setTextColor(context.getResources().getColor(R.color.gray));
            holder.msgDate.setTextColor(context.getResources().getColor(R.color.gray));
        }
        holder.msgTitle.setText(info.title);
        holder.msgContent.setText(info.content);
        holder.msgDate.setText(info.msg_date);
        ImageLoader.getInstance().displayImage(info.msg_pic, holder.msgPic, options);
        return convertView;
    }
    
    static class ViewHolder
    {
        @ViewInject(R.id.iv_msg_pic)
        ImageView msgPic;
        @ViewInject(R.id.tv_msg_title)
        TextView msgTitle;
        @ViewInject(R.id.tv_msg_detail)
        TextView msgContent;
        @ViewInject(R.id.tv_msg_time)
        TextView msgDate;
    }
    
}
