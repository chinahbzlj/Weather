package com.zhou.myweather.module.main;

import android.util.Log;

import com.zhou.myweather.model.CityManager;
import com.zhou.myweather.util.LogcatUtil;

/**
 * Created by 周利杰 on 17-11-16.
 */

public class CityManagerListenerManager {
    private static CityManagerListenerManager cityManagerListenerManager;

    private CityManagerListenerManager() {
    }

    private static synchronized void syncInit() {
        if (cityManagerListenerManager == null)
            cityManagerListenerManager = new CityManagerListenerManager();
    }

    public static CityManagerListenerManager getCityManagerListenerManager() {
        if (cityManagerListenerManager == null)
            syncInit();
        return cityManagerListenerManager;
    }

    private CityManagerListener cityManagerListener;

    public void setCityManagerListener(CityManagerListener cityManagerListener){
        this.cityManagerListener = cityManagerListener;
    }

    public CityManagerListener getCityManagerListener(){
        if(cityManagerListener == null) LogcatUtil.d("没有设置");
        return cityManagerListener;
    }

}
