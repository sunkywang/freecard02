package com.wxz.freecard.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil
{
    public static void showShort(Context context,String text)
    {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    
    public static void showShort(Context context,int res)
    {
        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
    }
}
