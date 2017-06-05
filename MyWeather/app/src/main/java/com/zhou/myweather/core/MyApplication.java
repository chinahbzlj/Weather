package com.zhou.myweather.core;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.zhou.myweather.location.LocationService;
import com.zhou.myweather.util.LeHandler;
import com.zhou.myweather.sdk.core.VolleySingleton;

/**
 * Created by Powerbee on 2016/5/10.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication = null;
    public VolleySingleton volleySingleton;

    public static MyApplication getInstance() {
        return myApplication;
    }

    //百度定位
//    public LocationService locationService;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
//        volleySingleton = VolleySingleton.getVolleySingleton(getApplicationContext());
        LeHandler.init(getApplicationContext());

        //初始化百度地图
//        SDKInitializer.initialize(getApplicationContext());
        /***
         * 初始化定位sdk，建议在Application中创建
         */
//        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
//        SDKInitializer.initialize(getApplicationContext());


    }
}
