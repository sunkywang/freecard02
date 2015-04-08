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
import com.wxz.freecard.bean.SellerInfo;

/**
 * @author Administrator
 * 首页的商家列表适配器
 */
public class MainFragmentListAdapter extends BasicListAdapter<SellerInfo>
{

    private DisplayImageOptions options;

    public MainFragmentListAdapter(Context context, List<SellerInfo> list)
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
        SellerInfo info = list.get(position);
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
        holder.sellerName.setText(info.name);
        holder.sellerDetail.setText(info.detail);
        ImageLoader.getInstance().displayImage(info.pic_url, holder.sellerPic, options);
        return convertView;
    }
    
    static class ViewHolder
    {
        @ViewInject(R.id.iv_seller_pic)
        ImageView sellerPic;
        @ViewInject(R.id.tv_seller_name)
        TextView sellerName;
        @ViewInject(R.id.tv_seller_detail)
        TextView sellerDetail;
    }
    
}
