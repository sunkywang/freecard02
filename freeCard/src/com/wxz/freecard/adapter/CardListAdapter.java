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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wxz.freecard.R;
import com.wxz.freecard.bean.MemberCard;

/**
 * @author Administrator
 * 
 */
public class CardListAdapter extends BasicListAdapter<MemberCard>
{
    public CardListAdapter(Context context, List<MemberCard> list)
    {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
    	MemberCard info = list.get(position);
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_list_item, parent, false);
            holder = new ViewHolder();
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.cardName.setText(info.cardName);
        ImageLoader.getInstance().displayImage(info.cardPic, holder.cardPic, options);
        return convertView;
    }
    
    static class ViewHolder
    {
        @ViewInject(R.id.iv_card_pic)
        ImageView cardPic;
        @ViewInject(R.id.tv_card_name)
        TextView cardName;
    }
    
}
