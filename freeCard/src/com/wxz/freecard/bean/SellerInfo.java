package com.wxz.freecard.bean;

import java.io.Serializable;

public class SellerInfo implements Serializable
{
    public String ID;
    public String pic_url;
    public String name;
    public String detail;
    public String getID()
    {
        return ID;
    }
    public void setID(String iD)
    {
        ID = iD;
    }
    public String getPic_url()
    {
        return pic_url;
    }
    public void setPic_url(String pic_url)
    {
        this.pic_url = pic_url;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getDetail()
    {
        return detail;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
    public MemberCard getCard()
    {
        return card;
    }
    public void setCard(MemberCard card)
    {
        this.card = card;
    }
    public MemberCard card;
}
