package com.wxz.freecard.utils;

import com.lidroid.xutils.util.LogUtils;

import android.os.Handler;
import android.support.v4.view.ViewPager;

public class AdsPlayer
{
    private static long DEFAULT_PLAYINTERVAL = 2000;
    
    private static boolean DEBUG = false;
    
    private int count;
    
    private int index;
    
    private long playInterval = DEFAULT_PLAYINTERVAL;
    
    private boolean pauseFlag = false;
    
    private boolean exitFlag = false;
    
    private ViewPager viewPager;
    
    private Handler handler;
    
    public AdsPlayer(ViewPager pager, Handler handler)
    {
        this.viewPager = pager;
        this.handler = handler;
        count = pager.getAdapter().getCount();
    }
    
    private Runnable handlerRunnable = new Runnable()
    {
        
        @Override
        public void run()
        {
            if (DEBUG)
                LogUtils.i("handlerRunnable");
            index = viewPager.getCurrentItem();
            index++;
            if (index == count)
            {
                index = 0;
            }
            viewPager.setCurrentItem(index, true);
        }
    };
    
    private Runnable threadRunnable = new Runnable()
    {
        
        @Override
        public void run()
        {
            while(!exitFlag)
            {
                if (DEBUG)
                    LogUtils.i("not exit");
                try
                {
                    Thread.sleep(playInterval);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (pauseFlag) continue;
                if (DEBUG)
                    LogUtils.i("not pause");
                handler.post(handlerRunnable);
            }
            
        }
    };
    
    private Thread switchThread = new Thread(threadRunnable);
    
    public void play()
    {
        switchThread.start();
        if (DEBUG)
            LogUtils.i("play");
    }
    
    public void resume()
    {
        pauseFlag = false;
        if (DEBUG)
            LogUtils.i("resume");
    }
    
    public void pause()
    {
        pauseFlag = true;
        if (DEBUG)
            LogUtils.i("pause");
    }
    
    public void release()
    {
        if (DEBUG)
            LogUtils.i("release");
        exitFlag = true;
        pauseFlag = true;
        handler.removeCallbacks(handlerRunnable);
    }
}
