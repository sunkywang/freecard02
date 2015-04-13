package com.wxz.freecard.database.table;

public class MsgTable implements IBaseTable
{
    public static final String TABLE_NAME = "msgtable";
    
    public static final String ID = "id";
    
    public static final String TYPE = "type";

    public static final String TITLE = "title";

    public static final String CONTENT = "content";
    
    public static final String PIC = "pic";

    public static final String DATE = "date";
    
    public static final String IS_READ = "is_read";

    @Override
    public String createTableString()
    {
        String create = "create table "
            +TABLE_NAME + "("
            +ID +" integer primary key, "
            +TYPE + " integer, "
            +IS_READ + " integer, "
            +TITLE + " text, "
            +CONTENT + " text, "
            +PIC + " text, "
            +DATE + " text"
            +")"
            ;
        return create;
    }

    @Override
    public String dropTableString()
    {
        String drop = "drop table if exists "+ TABLE_NAME;
        return drop;
    }
    
    
    
}
