package com.wxz.freecard.bean;

import java.io.Serializable;

public class MemberCard implements Serializable
{
    public int ID;
    
    public String cardNumber;
    
    public String cardPic;
    
    public String cardName;

    public int getID()
    {
        return ID;
    }

    public void setID(int iD)
    {
        ID = iD;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public String getCardPic()
    {
        return cardPic;
    }

    public void setCardPic(String cardPic)
    {
        this.cardPic = cardPic;
    }

    public String getCardName()
    {
        return cardName;
    }

    public void setCardName(String cardName)
    {
        this.cardName = cardName;
    }
}
