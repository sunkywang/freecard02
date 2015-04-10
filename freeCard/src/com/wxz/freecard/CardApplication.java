package com.wxz.freecard;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.lidroid.xutils.util.LogUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wxz.freecard.bean.LocationInfo;

/**
 * @author Administrator
 *
 */
public class CardApplication extends Application
{
    private static CardApplication instance;
    
    public LocationClient mLocationClient;
    
    public MyLocationListener mMyLocationListener;
    
    private LocationInfo locationInfo = new LocationInfo();
    
    public LocationInfo getLocationInfo()
    {
        return locationInfo;
    }
    
    public static CardApplication getInstance()
    {
        return instance;
    }
    
    public interface OnLocationReceivedListener
    {
        void onReceived();
    }
    
    private List<OnLocationReceivedListener> locationListeners = new ArrayList<CardApplication.OnLocationReceivedListener>();
    
    public void addLocationReceivedListener(OnLocationReceivedListener listener)
    {
        locationListeners.add(listener);
    }
    
    public void removeLocationReceivedListener(OnLocationReceivedListener listener)
    {
        locationListeners.remove(listener);
    }
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        initImageLoader(this);
        initLocationOptions();
        locationInfo.loadFromPreference();
    }
    
    private void initLocationOptions()
    {
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
        option.setCoorType("gcj02");//返回的定位结果是百度经纬度，默认值gcj02
        option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
    
    /**
     * 初始化imageLoader
     */
    public void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
    
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location 
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation){
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\ndirection : ");
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append(location.getDirection());
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
            }
            LogUtils.v(sb.toString());
            locationInfo.lat = location.getLatitude();
            locationInfo.lng = location.getLongitude();
            String city = location.getCity();
            if (city != null && city.contains("市"))
            {
                city = city.replace("市", "");
            }
            locationInfo.city = city;
            locationInfo.saveToPrefrence();
            notifyAllLocationListeners();
        }


    }
    
    private void notifyAllLocationListeners()
    {
        for(OnLocationReceivedListener listener : locationListeners)
        {
            listener.onReceived();
        }
    }
    
}


