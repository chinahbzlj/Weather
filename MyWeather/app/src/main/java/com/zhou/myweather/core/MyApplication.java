package com.zhou.myweather.core;

import android.app.Application;
import android.app.Service;
import android.content.res.AssetManager;
import android.os.Vibrator;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhou.myweather.db.WeatherDAO;
import com.zhou.myweather.db.dto.CityPO;
import com.zhou.myweather.model.mos.CityMO;
import com.zhou.myweather.util.ExcelUtil;
import com.zhou.myweather.util.FileManagerUtils;
import com.zhou.myweather.util.LeHandler;
import com.zhou.myweather.util.LogcatUtil;
import com.zhou.myweather.util.ToastUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by Powerbee on 2016/5/10.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication = null;

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
//        Logger.init("Weather");
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化百度地图
//        SDKInitializer.initialize(getApplicationContext());
        /***
         * 初始化定位sdk，建议在Application中创建
         */
//        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
//        SDKInitializer.initialize(getApplicationContext());
        ToastUtil.getInstance().setApplicatonContext(this);
        WeatherDAO.getWeatherDAO().init(this);

        CrashReport.initCrashReport(getApplicationContext(), "df16bf03c5", true);
    }

}
