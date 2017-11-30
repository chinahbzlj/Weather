package com.zhou.myweather.core;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Vibrator;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhou.myweather.db.WeatherDAO;
import com.zhou.myweather.db.dto.CityPO;
import com.zhou.myweather.model.mos.CityMO;
import com.zhou.myweather.service.LocationService;
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

    public LocationService locationService;
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
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        ToastUtil.getInstance().setApplicatonContext(this);
        WeatherDAO.getWeatherDAO().init(this);

//        CrashReport.initCrashReport(getApplicationContext(), "df16bf03c5", true);
        Bugly.init(getApplicationContext(), "df16bf03c5", false);
    }


//    Beta.upgradeListener = new UpgradeListener() {
//        @Override
//        public void onUpgrade(int ret,UpgradeInfo strategy, boolean isManual, boolean isSilence) {
//            if (strategy != null) {
//                Intent i = new Intent();
//                i.setClass(getApplicationContext(), UpgradeActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//            } else {
//                Toast.makeText(BaseApplication.this, "没有更新", Toast.LENGTH_LONG).show();
//            }
//        }
//    };
//
///* 设置更新状态回调接口 */
//    Beta.upgradeStateListener = new UpgradeStateListener() {
//        @Override
//        public void onUpgradeSuccess(boolean isManual) {
//            Toast.makeText(getApplicationContext(),"UPGRADE_SUCCESS",Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onUpgradeFailed(boolean isManual) {
//            Toast.makeText(getApplicationContext(),"UPGRADE_FAILED",Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onUpgrading(boolean isManual) {
//            Toast.makeText(getApplicationContext(),"UPGRADE_CHECKING",Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onUpgradeNoVersion(boolean isManual) {
//            Toast.makeText(getApplicationContext(),"UPGRADE_NO_VERSION",Toast.LENGTH_SHORT).show();
//        }
//    };
}
