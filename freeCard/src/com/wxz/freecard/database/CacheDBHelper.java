package com.wxz.freecard.database;

import java.util.ArrayList;
import java.util.List;

import com.wxz.freecard.database.table.IBaseTable;
import com.wxz.freecard.database.table.MsgTable;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CacheDBHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "card.db";
    
    private static final int DB_VERSION = 1;
    
    private List<IBaseTable> tables = new ArrayList<IBaseTable>();

    public CacheDBHelper(Context context, String name, CursorFactory factory, int version,
        DatabaseErrorHandler errorHandler)
    {
        super(context, name, factory, version, errorHandler);
        MsgTable msgtable = new MsgTable();
        tables.add(msgtable);
    }

    public CacheDBHelper(Context context, String name, CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    public CacheDBHelper(Context context)
    {
        this(context, DB_NAME, null, DB_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        for (IBaseTable table : tables)
        {
            db.execSQL(table.createTableString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        for (IBaseTable table : tables)
        {
            db.execSQL(table.dropTableString());
        }
        onCreate(db);
    }
    
}
