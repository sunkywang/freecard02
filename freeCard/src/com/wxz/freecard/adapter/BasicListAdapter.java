package com.wxz.freecard.adapter;

import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

public abstract class BasicListAdapter<T> extends BaseAdapter
{

    protected Context context;
    
    protected List<T> list;
    
    public BasicListAdapter(Context context, List<T> list)
    {
        this.context = context;
        this.list = list;
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
