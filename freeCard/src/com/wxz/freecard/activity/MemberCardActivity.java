package com.wxz.freecard.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.wxz.freecard.R;
import com.wxz.freecard.utils.QRCodeUtil;
import com.zxing.WriterException;

public class MemberCardActivity extends BaseActivity
{
    
    @ViewInject(R.id.title)
    private TextView tvTitle;
    @ViewInject(R.id.iv_back)
    private ImageView ivBack;
    @ViewInject(R.id.iv_member_card)
    private ImageView ivMemberCard;
    @ViewInject(R.id.iv_member_card_qrcode)
    private ImageView ivMemberCardQrcode;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_card_activity);
        ViewUtils.inject(this);
        setupView();
    }
    
    private void setupView()
    {
        tvTitle.setText("会员卡");
        try
        {
            ivMemberCardQrcode.setImageBitmap(QRCodeUtil.Create2DCode("http://www.baidu.com"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void flipCard()
    {
        boolean isCardShown = ivMemberCard.getVisibility() == View.VISIBLE;
        final ImageView visible = isCardShown ? ivMemberCard : ivMemberCardQrcode;
        final ImageView invisible = isCardShown ? ivMemberCardQrcode : ivMemberCard;
        final ObjectAnimator invisiToVisi = ObjectAnimator.ofFloat(invisible, "rotationY",-90f, 0f); 
        final ObjectAnimator visiToInvisi = ObjectAnimator.ofFloat(visible, "rotationY", 0f, 90f); 
        invisiToVisi.setDuration(500);
        invisiToVisi.setInterpolator(new DecelerateInterpolator());
        visiToInvisi.setDuration(500);
        visiToInvisi.setInterpolator(new AccelerateInterpolator());
        visiToInvisi.addListener(new AnimatorListener()
        {
            
            @Override
            public void onAnimationStart(Animator animation)
            {
            }
            
            @Override
            public void onAnimationRepeat(Animator animation)
            {
            }
            
            @Override
            public void onAnimationEnd(Animator animation)
            {
                visible.setVisibility(View.GONE);
                invisiToVisi.start();
                invisible.setVisibility(View.VISIBLE);
            }
            
            @Override
            public void onAnimationCancel(Animator animation)
            {
            }
        });
        visiToInvisi.start();
    }
    
    @OnClick({R.id.iv_back,R.id.iv_member_card,R.id.iv_member_card_qrcode})
    private void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                
                break;
            case R.id.iv_member_card:
            case R.id.iv_member_card_qrcode:
                flipCard();
                break;
            default:
                break;
        }
    }
}
