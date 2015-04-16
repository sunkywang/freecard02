package com.wxz.freecard.bean;

import java.io.Serializable;

import com.wxz.freecard.manager.SharedPreferenceManager;
import com.wxz.freecard.manager.SharedPreferenceManager.SP_Const;

public class LocationInfo implements Serializable
{
    public double lat;
    
    public double lng;
    
    public String city="";
    
    public String setCity="";
    
    public void saveToPrefrence()
    {
        SharedPreferenceManager spManager = SharedPreferenceManager.getInstance();
        spManager.putFloat(SP_Const.LAT, (float)lat);
        spManager.putFloat(SP_Const.LNG, (float)lng);
        spManager.putString(SP_Const.SETCITY, setCity);
        spManager.putString(SP_Const.CITY, city);
        spManager.commit();
    }
    
    public void loadFromPreference()
    {
        SharedPreferenceManager spManager = SharedPreferenceManager.getInstance();
        lat = spManager.getFloat(SP_Const.LAT, -1);
        lng = spManager.getFloat(SP_Const.LNG, -1);
        city = spManager.getString(SP_Const.CITY,"南京");
        city = spManager.getString(SP_Const.SETCITY,"");
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getSetCity()
    {
        return setCity;
    }

    public void setSetCity(String setCity)
    {
        this.setCity = setCity;
    }
    
}
