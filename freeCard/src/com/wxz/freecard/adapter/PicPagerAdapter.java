package com.wxz.freecard.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wxz.freecard.bean.SellerInfo;

/**
 * @author wxz
 * 广告切换适配器
 *
 */
public class PicPagerAdapter extends PagerAdapter
{
    
    private List<SellerInfo> list;
    
    public PicPagerAdapter(List<SellerInfo> list)
    {
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return list == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        ImageView image = new ImageView(container.getContext());
        image.setLayoutParams(new LayoutParams(-1, -1));
        container.addView(image);
        ImageLoader.getInstance().displayImage(list.get(position % list.size()).pic_url, image);
        return image;
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View)object);
    }
    
}
