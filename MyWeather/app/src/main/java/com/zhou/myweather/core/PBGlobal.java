package com.zhou.myweather.core;

import android.content.Context;

import com.zhou.myweather.model.DatabaseHelper;
import com.zhou.myweather.model.dao.CityInfoDao;
import com.zhou.myweather.model.dao.CityWeatherDao;
import com.zhou.myweather.model.dao.ForecastDao;
import com.zhou.myweather.model.datas.DataFoecast;
import com.zhou.myweather.model.datas.DataLocalCity;
import com.zhou.myweather.model.datas.DataLocalWeather;
import com.zhou.sdk.core.HttpEngine;
import com.zhou.sdk.core.VolleySingleton;
import com.zhou.sdk.defines.Config;

/**
 * Created by Powerbee on 2016/5/10.
 */
public class PBGlobal {
    public static boolean isOne = true;
    /**
     * 是否需要返回七天数据中的后四天 0表示不返回 1表示返回
     */
    public static String needMoreDay = Config.ON;
    /**
     * 是否需要返回指数数据 0表示不返回 1表示返回
     */
    public static String needIndex = Config.OFF;
    /**
     * 是否需要每小时数据的累积数组 因为半小时刷一次实时状态，所以数组的最大长度为48，每天0点长度初始化为0。 0表示不返回 1表示返回
     */
    public static String needHourData = Config.OFF;
    private static PBGlobal pbGlobal = null;
    private Context appContext;
    private DataLocalCity dataLocalCity;
    private DataLocalWeather dataLocalWeather;
    private DataFoecast dataFoecast;

    private PBGlobal() {
    }

    private static synchronized void syncInit() {
        if (pbGlobal == null) {
            pbGlobal = new PBGlobal();
        }
    }

    public static PBGlobal getPbGlobal() {
        if (pbGlobal == null)
            syncInit();
        return pbGlobal;
    }

    public void init(Context appContext) {
        this.appContext = appContext;
        DatabaseHelper.initDataBase(appContext);
        dataLocalCity = new DataLocalCity();
        dataLocalCity.setAllLocalCitys(CityInfoDao.queryAllCity());
        dataLocalWeather = new DataLocalWeather();
        dataLocalWeather.setAllLocalWeathers(CityWeatherDao.queryAllCity());
        dataFoecast = new DataFoecast();
        dataFoecast.setAllForecast(ForecastDao.queryAllCity());
    }

    public DataLocalCity getDataLocalCity() {
        return dataLocalCity;
    }

    public DataLocalWeather getDataLocalWeather() {
        return dataLocalWeather;
    }
    public DataFoecast getDataFoecast(){return dataFoecast;}

    public VolleySingleton getVolley() {
        return MyApplication.getInstance().volleySingleton;
    }

    public HttpEngine getHttpEngine() {
        return HttpEngine.getEngine();
    }

    public void setAppContext(Context context) {
        this.appContext = context;
    }

    public Context getAppContext() {
        return this.appContext;
    }

    public void exitSystem() {
        DatabaseHelper.getDBHelper().close();
    }
}
