package com.wxz.freecard.manager;

import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import com.wxz.freecard.CardApplication;

public class SharedPreferenceManager
{
    public static class SP_Const
    {
        public static final String NAME = "cardbag_prefrence.sp"; 
        
        public static final String CITY = "city";
        
        public static final String SETCITY = "set_city";
        
        public static final String LAT = "lat";
        
        public static final String LNG = "lng";
    }
    
    private static SharedPreferenceManager manager;
    
    private SharedPreferences mSharedPreferences;
    
    private SharedPreferences.Editor editor;
    
    private SharedPreferenceManager()
    {
        mSharedPreferences = CardApplication.getInstance().getSharedPreferences(SP_Const.NAME, Context.MODE_PRIVATE);
    }
    
    public static SharedPreferenceManager getInstance()
    {
        if (manager == null)
        {
            manager = new SharedPreferenceManager();
        }
        return manager; 
    }

    public Editor putString(String key, String value)
    {
        return getEditor().putString(key, value);
    }

    public Editor putStringSet(String key, Set<String> values)
    {
        return getEditor().putStringSet(key, values);
    }

    public Editor putInt(String key, int value)
    {
        return getEditor().putInt(key, value);
    }

    public Editor putLong(String key, long value)
    {
        return getEditor().putLong(key, value);
    }

    public Editor putFloat(String key, float value)
    {
        return getEditor().putFloat(key, value);
    }

    public Editor putBoolean(String key, boolean value)
    {
        return getEditor().putBoolean(key, value);
    }

    public Editor remove(String key)
    {
        return getEditor().remove(key);
    }

    public Editor clear()
    {
        return getEditor().clear();
    }

    public boolean commit()
    {
        return getEditor().commit();
    }

    public void apply()
    {
        getEditor().apply();
    }

    private Editor getEditor()
    {
        if (editor == null)
        {
            edit();
        }
        return editor;
    }
    
    public Map<String, ?> getAll()
    {
        return mSharedPreferences.getAll();
    }

    public String getString(String key, String defValue)
    {
        return mSharedPreferences.getString(key, defValue);
    }

    public Set<String> getStringSet(String key, Set<String> defValues)
    {
        return mSharedPreferences.getStringSet(key, defValues);
    }

    public int getInt(String key, int defValue)
    {
        return mSharedPreferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue)
    {
        return mSharedPreferences.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue)
    {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue)
    {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public boolean contains(String key)
    {
        return mSharedPreferences.contains(key);
    }

    public Editor edit()
    {
        editor = mSharedPreferences.edit();
        return editor;
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener)
    {
        mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener)
    {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
    
    
}
