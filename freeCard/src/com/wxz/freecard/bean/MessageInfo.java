package com.wxz.freecard.bean;


public class MessageInfo {
	public static final int USER = 0x1;
	public static final int SYSTEM = 0x2;
	public static final int Seller = 0x3;
	
	public int type;
	
	public int id;
	
	public String title;
	
	public String content;
	
	public String msg_pic;
	
	public String msg_date;
	
	public boolean isRead;
}
