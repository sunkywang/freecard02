package com.wxz.freecard.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lidroid.xutils.util.LogUtils;
import com.wxz.freecard.CardApplication;
import com.wxz.freecard.bean.MessageInfo;
import com.wxz.freecard.database.table.MsgTable;

public class DBManager
{
    private static DBManager instance;
    
    public static DBManager getInstance()
    {
        if (instance == null)
        {
            instance = new DBManager(CardApplication.getInstance());
        }
        return instance;
    }
    
    private MainDBHelper mHelper;
    
    private DBManager(Context context)
    {
        mHelper = new MainDBHelper(context);
    }
    
    public List<MessageInfo> getMessageInfos()
    {
        List<MessageInfo> list = new ArrayList<MessageInfo>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor c = db.query(MsgTable.TABLE_NAME, null, null, null, null, null, null);
        try
        {
            if (c.moveToFirst())
            {
                do
                {
                    MessageInfo info = new MessageInfo();
                    info.id = c.getInt(c.getColumnIndex(MsgTable.ID));
                    info.title = c.getString(c.getColumnIndex(MsgTable.TITLE));
                    info.content = c.getString(c.getColumnIndex(MsgTable.TITLE));
                    info.msg_date = c.getString(c.getColumnIndex(MsgTable.TITLE));
                    info.type = c.getInt(c.getColumnIndex(MsgTable.TYPE));
                    info.isRead = c.getInt(c.getColumnIndex(MsgTable.IS_READ)) == 1;
                    list.add(info);
                } while (c.moveToNext());
            }
        }
        catch (Exception e)
        {
            LogUtils.e("getMessageInfos", e);
        }
        finally
        {
            c.close();
            db.close();
        }
        return list;
    }
    
}
