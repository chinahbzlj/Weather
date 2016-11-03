package com.zhou.myweather.model.datas;

import android.text.TextUtils;

import com.zhou.myweather.model.mos.CityWeatherMO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Powerbee on 2016/6/12.
 */
public class DataLocalWeather {
    /**
     * 保存已查询到的天气
     */

    private Map<String, CityWeatherMO> localWeatherMap = new HashMap<>();


    /**
     * 设置所有城市的天气
     *
     * @param cityWeatherMOs
     */
    public void setAllLocalWeathers(List<CityWeatherMO> cityWeatherMOs) {
        if (cityWeatherMOs == null) return;
        this.localWeatherMap.clear();
        for (CityWeatherMO cityWeatherMO : cityWeatherMOs) {
            localWeatherMap.put(cityWeatherMO.name, cityWeatherMO);
        }
    }

    public void addCityWeather(CityWeatherMO cityWeatherMO) {
        if (cityWeatherMO == null) return;
        this.localWeatherMap.put(cityWeatherMO.name, cityWeatherMO);
    }

    /**
     * 查询城市天气
     *
     * @param cityWeatherMO
     * @return
     */
    public CityWeatherMO queryCityWeather(CityWeatherMO cityWeatherMO) {
        if (cityWeatherMO == null) return null;
        return localWeatherMap.get(cityWeatherMO.name);
    }

    public CityWeatherMO queryCityWeather(String name) {
        if (TextUtils.isEmpty(name)) return null;
        return localWeatherMap.get(name);
    }

}
