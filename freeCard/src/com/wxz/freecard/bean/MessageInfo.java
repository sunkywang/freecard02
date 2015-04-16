package com.wxz.freecard.bean;

import java.io.Serializable;


public class MessageInfo implements Serializable{
	public static final int USER = 0x1;
	public static final int SYSTEM = 0x2;
	public static final int Seller = 0x3;
	
	public int type;
	
	public int id;
	
	public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getMsg_pic()
    {
        return msg_pic;
    }

    public void setMsg_pic(String msg_pic)
    {
        this.msg_pic = msg_pic;
    }

    public String getMsg_date()
    {
        return msg_date;
    }

    public void setMsg_date(String msg_date)
    {
        this.msg_date = msg_date;
    }

    public boolean isRead()
    {
        return isRead;
    }

    public void setRead(boolean isRead)
    {
        this.isRead = isRead;
    }

    public String title;
	
	public String content;
	
	public String msg_pic;
	
	public String msg_date;
	
	public boolean isRead;
}
