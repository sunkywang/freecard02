package com.wxz.freecard.activity.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.wxz.freecard.R;
import com.wxz.freecard.activity.MemberCardActivity;
import com.wxz.freecard.adapter.CardListAdapter;
import com.wxz.freecard.adapter.CouponListAdapter;
import com.wxz.freecard.bean.Coupon;
import com.wxz.freecard.bean.MemberCard;

public class CardBagFragment extends BaseFragment
{
    @ViewInject(R.id.title)
    private TextView tvTitle;
    @ViewInject(R.id.rg_card_bag)
    private RadioGroup rgMain;
    @ViewInject(R.id.rg_card_sub)
    private RadioGroup rgCard;
    @ViewInject(R.id.rg_coupon_sub)
    private RadioGroup rgCoupon;
    @ViewInject(R.id.list)
    private ListView list;
    
    private List<MemberCard> cardList = new ArrayList<MemberCard>();
    private List<Coupon> couponList = new ArrayList<Coupon>();
    private CardListAdapter cardListAdapter;
    private CouponListAdapter couponListAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.card_bag_fragment, container, false);
        ViewUtils.inject(this, view);
        setupView();
        return view;
    }
    
    private void setupView()
    {
        tvTitle.setText("卡包");
        getLists();
        cardListAdapter = new CardListAdapter(getActivity(), cardList);
        couponListAdapter = new CouponListAdapter(getActivity(), couponList);
        list.setAdapter(cardListAdapter);
    }
    
    private void getLists()
    {
        MemberCard card = new MemberCard();
        card.cardName = "会员卡1";
        card.cardNumber = "78883763";
        card.cardPic = "http://img2.imgtn.bdimg.com/it/u=1545224801,1573612251&fm=21&gp=0.jpg";
        cardList.add(card);
        card = new MemberCard();
        card.cardName = "会员卡2";
        card.cardNumber = "98598584";
        card.cardPic = "http://img2.imgtn.bdimg.com/it/u=1214043058,2905808164&fm=21&gp=0.jpg";
        cardList.add(card);
        card = new MemberCard();
        card.cardName = "会员卡3";
        card.cardNumber = "98598584";
        card.cardPic = "http://img3.imgtn.bdimg.com/it/u=2282342495,3445837868&fm=21&gp=0.jpg";
        cardList.add(card);
        card = new MemberCard();
        card.cardName = "会员卡4";
        card.cardNumber = "98598584";
        card.cardPic = "http://img3.imgtn.bdimg.com/it/u=3743714790,2321018617&fm=21&gp=0.jpg";
        cardList.add(card);
        card = new MemberCard();
        card.cardName = "会员卡5";
        card.cardNumber = "98598584";
        card.cardPic = "http://img2.imgtn.bdimg.com/it/u=260162639,710953389&fm=21&gp=0.jpg";
        cardList.add(card);
        
        Coupon coupon = new Coupon();
        coupon.sellerName = "商家1";
        coupon.info = "8折卡";
        coupon.condition = "消费满100";
        coupon.couponPic = "http://img0.imgtn.bdimg.com/it/u=1123374333,1641533781&fm=21&gp=0.jpg";
        couponList.add(coupon);
        coupon = new Coupon();
        coupon.sellerName = "商家2";
        coupon.info = "7折卡";
        coupon.condition = "消费满100";
        coupon.couponPic = "http://img5.imgtn.bdimg.com/it/u=944433258,2296329833&fm=21&gp=0.jpg";
        couponList.add(coupon);
        coupon = new Coupon();
        coupon.sellerName = "商家3";
        coupon.info = "7折卡";
        coupon.condition = "消费满100";
        coupon.couponPic = "http://img1.imgtn.bdimg.com/it/u=333524197,1798056612&fm=21&gp=0.jpg";
        couponList.add(coupon);
        coupon = new Coupon();
        coupon.sellerName = "商家4";
        coupon.info = "7折卡";
        coupon.condition = "消费满100";
        coupon.couponPic = "http://img3.imgtn.bdimg.com/it/u=973731271,2831105480&fm=21&gp=0.jpg";
        couponList.add(coupon);
        coupon = new Coupon();
        coupon.sellerName = "商家5";
        coupon.info = "7折卡";
        coupon.condition = "消费满100";
        coupon.couponPic = "http://img3.imgtn.bdimg.com/it/u=973731271,2831105480&fm=21&gp=0.jpg";
        couponList.add(coupon);
    }
    
    @OnRadioGroupCheckedChange({R.id.rg_card_bag,R.id.rg_card_sub,R.id.rg_coupon_sub})
    private void onCheckedChanged(RadioGroup group, int checkedId)
    {
        if (group == rgMain)
        {
            list.scrollTo(0, 0);
            if (checkedId == R.id.card_tab)
            {
                rgCard.setVisibility(View.VISIBLE);
                rgCoupon.setVisibility(View.GONE);
                list.setAdapter(cardListAdapter);
            }
            else
            {
                rgCard.setVisibility(View.GONE);
                rgCoupon.setVisibility(View.VISIBLE);
                list.setAdapter(couponListAdapter);
            }
        }
        else if (group == rgCard)
        {
            
        }
        else if (group == rgCoupon)
        {
            
        }
    }
    
    @OnItemClick(R.id.list)
    private void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    {
        if (rgMain.getCheckedRadioButtonId() == R.id.card_tab)
        {
            Intent intent = new Intent(getActivity(), MemberCardActivity.class);
            startActivity(intent);
        }
        else
        {
            
        }
    }
}
