package com.wxz.freecard.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.wxz.freecard.R;

import android.content.Context;
import android.widget.BaseAdapter;

public abstract class BasicListAdapter<T> extends BaseAdapter
{

    protected Context context;
    
    protected List<T> list;

    protected DisplayImageOptions options;
    
    public BasicListAdapter(Context context, List<T> list)
    {
        this.context = context;
        this.list = list;
        options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_launcher)
        .showImageForEmptyUri(R.drawable.ic_launcher)
        .showImageOnFail(R.drawable.ic_launcher)
        .cacheInMemory(true)
        .cacheOnDisk(true)
//        .bitmapConfig(Bitmap.Config.RGB_565)
        .build();
    }
    
    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

}
