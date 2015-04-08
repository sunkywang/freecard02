package com.wxz.freecard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * @author Administrator
 *
 */
public class CardApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        initImageLoader(this);
    }
    
    
    /**
     * 初始化imageLoader
     */
    public void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
    
    
    
}


