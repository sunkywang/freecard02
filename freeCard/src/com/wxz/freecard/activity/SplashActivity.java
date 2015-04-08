package com.wxz.freecard.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.wxz.freecard.R;

public class SplashActivity extends Activity
{
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        findViewById(R.id.iv_splash).startAnimation(anim);
        new Handler().postDelayed(new Runnable()
        {
            
            @Override
            public void run()
            {
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 1500);
    }
    
   
}
