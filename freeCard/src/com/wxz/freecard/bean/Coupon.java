package com.wxz.freecard.bean;

import java.io.Serializable;

public class Coupon implements Serializable
{
    public int ID;
    
    public String info;
    
    public String condition;
    
    public String couponPic;
    
    public String sellerName;
    
    public int sellerId;

    public int getID()
    {
        return ID;
    }

    public void setID(int iD)
    {
        ID = iD;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public String getCouponPic()
    {
        return couponPic;
    }

    public void setCouponPic(String couponPic)
    {
        this.couponPic = couponPic;
    }

    public String getSellerName()
    {
        return sellerName;
    }

    public void setSellerName(String sellerName)
    {
        this.sellerName = sellerName;
    }

    public int getSellerId()
    {
        return sellerId;
    }

    public void setSellerId(int sellerId)
    {
        this.sellerId = sellerId;
    }
}
