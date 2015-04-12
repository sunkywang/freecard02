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

    private DisplayImageOptions options;

    public MsgListAdapter(Context context, List<MessageInfo> list)
    {
        super(context, list);
        options = new DisplayImageOptions.Builder()
//        .showImageOnLoading(R.drawable.ic_stub)
//        .showImageForEmptyUri(R.drawable.ic_empty)
//        .showImageOnFail(R.drawable.ic_error)
        .cacheInMemory(true)
        .cacheOnDisk(true)
//        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
    	MessageInfo info = list.get(position);
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.seller_item, parent, false);
            holder = new ViewHolder();
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.msgTitle.setText(info.title);
        holder.msgContent.setText(info.content);
        ImageLoader.getInstance().displayImage(info.msg_pic, holder.msgPic, options);
        return convertView;
    }
    
    static class ViewHolder
    {
        @ViewInject(R.id.iv_seller_pic)
        ImageView msgPic;
        @ViewInject(R.id.tv_seller_name)
        TextView msgTitle;
        @ViewInject(R.id.tv_seller_detail)
        TextView msgContent;
        @ViewInject(R.id.tv_seller_detail)
        TextView msgDate;
    }
    
}
