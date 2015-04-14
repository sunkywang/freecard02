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
import com.wxz.freecard.bean.Coupon;

/**
 * @author Administrator
 * 
 */
public class CouponListAdapter extends BasicListAdapter<Coupon>
{
    public CouponListAdapter(Context context, List<Coupon> list)
    {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
    	Coupon info = list.get(position);
        ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.coupon_list_item, parent, false);
            holder = new ViewHolder();
            ViewUtils.inject(holder, convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.couponSellerName.setText(info.sellerName);
        holder.couponName.setText(info.info);
        holder.couponDetail.setText(info.condition);
        ImageLoader.getInstance().displayImage(info.couponPic, holder.couponPic, options);
        return convertView;
    }
    
    static class ViewHolder
    {
        @ViewInject(R.id.iv_coupon_pic)
        ImageView couponPic;
        @ViewInject(R.id.tv_coupon_name)
        TextView couponName;
        @ViewInject(R.id.tv_coupon_detail)
        TextView couponDetail;
        @ViewInject(R.id.tv_seller_name)
        TextView couponSellerName;
        
    }
    
}
