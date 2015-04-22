package com.lee.pullrefresh.ui;

import com.lee.pullrefresh.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class RadView extends View
{
    private Paint mPaint;
    
    private float strokeWidth = 1.5f;
    
    private int color = Color.GRAY;
    
    private float angle;
    
    private RectF mRectF;
    
    private Drawable arrowDrawable;
    
    private boolean isLoadingMode;
    
    private float rotateAngle;
    
    public RadView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    public RadView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }
    
    public RadView(Context context)
    {
        this(context, null);
    }
    
    private void init()
    {
        float density = getResources().getDisplayMetrics().density;
        mPaint = new Paint();
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(strokeWidth * density);
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStrokeCap(Cap.ROUND);
        arrowDrawable = getResources().getDrawable(R.drawable.arrow);
    }
    
    public void setAngle(float angle)
    {
        this.angle = angle;
        invalidate();
    }
    
    Handler handler = new Handler();
    
    Runnable loadRunnable = new Runnable()
    {
        
        @Override
        public void run()
        {
            if (isLoadingMode)
            {
                rotateAngle += 10;
                if (rotateAngle > 360)
                {
                    rotateAngle -= 360;
                }
                invalidate();
                handler.postDelayed(this, 14);
            }
        }
    };
    public void startLoading()
    {
        if (isLoadingMode)
            return;
        isLoadingMode = true;
        
        handler.post(loadRunnable);
//        new Thread()
//        {
//            public void run()
//            {
//                while (isLoadingMode)
//                {
//                    rotateAngle += 5;
//                    if (rotateAngle > 360)
//                    {
//                        rotateAngle -= 360;
//                    }
//                    postInvalidate();
//                    try
//                    {
//                        Thread.sleep(14);
//                    }
//                    catch (InterruptedException e)
//                    {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
    }
    
    public void stopLoading()
    {
        isLoadingMode = false;
        rotateAngle = 0;
        handler.removeCallbacks(loadRunnable);
    }
    
    @Override
    protected void onDraw(Canvas canvas)
    {
        if (mRectF == null)
        {
            float offset = mPaint.getStrokeWidth();
            mRectF = new RectF(offset, offset, getWidth() - offset, getHeight() - offset);
            arrowDrawable.setBounds(new Rect((int)mRectF.left, (int)mRectF.top, (int)mRectF.right, (int)mRectF.bottom));
        }
        if (!isLoadingMode)
        {
            arrowDrawable.draw(canvas);
            //            canvas.drawArc(mRectF, -90, angle, false, mPaint);
            final float blankAngle = 20;//10度的留白
            if (angle < blankAngle / 2)
            {
                angle = 0;
            }
            else if (angle < 360 - blankAngle)
            {
                angle = angle - blankAngle / 2;
            }
            else
            {
                angle = 360 - blankAngle;
            }
            canvas.drawArc(mRectF, -90 + blankAngle / 2, angle, false, mPaint);
        }
        else
        {
            canvas.save();
            canvas.rotate(rotateAngle,getWidth() / 2, getHeight() / 2);
            canvas.drawArc(mRectF, -80, 340, false, mPaint);
            canvas.restore();
        }
        super.onDraw(canvas);
    }
}
