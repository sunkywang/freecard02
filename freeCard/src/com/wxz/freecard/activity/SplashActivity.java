package com.wxz.freecard.activity;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.wxz.freecard.R;
import com.wxz.freecard.utils.FileUtil;

public class SplashActivity extends BaseActivity
{
    @ViewInject(R.id.iv_splash)
    private ImageView ivSplash;
    
    private Animation anim;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewUtils.inject(this);
        anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        loadSplashBitmap();
    }
    
    private void loadSplashBitmap()
    {
        refreshSplash();
        File file = FileUtil.getSplashFile();
        if (file.exists())
        {
            try
            {
                Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
                ivSplash.setImageBitmap(bmp);
            }
            catch (Exception e)
            {
            }
        }
        else
        {
            ivSplash.setImageResource(R.drawable.bg);
        }
        ivSplash.startAnimation(anim);
        new Handler().postDelayed(new Runnable()
        {
            
            @Override
            public void run()
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }
    
    private void refreshSplash()
    {
        
    }
   
}
