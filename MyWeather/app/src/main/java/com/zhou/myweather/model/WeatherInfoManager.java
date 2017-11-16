package com.zhou.myweather.model;

import com.zhou.myweather.module.main.weather.WeatherPOJO;
import com.zhou.myweather.net.CityAllWeatherInfoDTO;
import com.zhou.myweather.util.LogcatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 周利杰 on 2017/9/30.
 */

public class WeatherInfoManager {
    private static WeatherInfoManager weatherInfoManager;
    private Map<String, WeatherPOJO> weatherInfoDTOHashMap;

    private WeatherInfoManager() {
        weatherInfoDTOHashMap = new LinkedHashMap<>();
    }

    private static synchronized void syncInit() {
        if (weatherInfoManager == null)
            weatherInfoManager = new WeatherInfoManager();
    }

    public static WeatherInfoManager getWeatherInfoManager() {
        if (weatherInfoManager == null)
            syncInit();
        return weatherInfoManager;
    }

    public Map<String, WeatherPOJO> getWeatherInfoDTOHashMap() {
        return weatherInfoDTOHashMap;
    }

    public List<WeatherPOJO> getWeatherMap() {
        List<WeatherPOJO> weatherPOJOS = new ArrayList<>();
        weatherPOJOS.addAll(weatherInfoDTOHashMap.values());
        return weatherPOJOS;
    }

    public boolean removePOJO(int position) {
        WeatherPOJO weatherPOJO = getWeatherMap().get(position);
        Iterator<WeatherPOJO> it = getWeatherMap().iterator();
        while (it.hasNext()) {
            if (it.next().city_name.equals(weatherPOJO.city_name)) {
                it.remove();
                weatherInfoDTOHashMap.remove(weatherPOJO.city_name);
                return true;
            }
        }
        return false;
    }

    public void addCityWeather(WeatherPOJO weatherPOJO) {
        weatherInfoDTOHashMap.put(weatherPOJO.city_name, weatherPOJO);
    }

    public WeatherPOJO getWeatherPOJO(String cityName) {
        WeatherPOJO weatherPOJO = weatherInfoDTOHashMap.get(cityName);
        return weatherPOJO;
    }

    public void removeCity(String cityName) {
        if (weatherInfoDTOHashMap.containsKey(cityName))
            weatherInfoDTOHashMap.remove(cityName);
    }

    public void addCity(String city) {
        weatherInfoDTOHashMap.put(city, null);
    }

    public void clear() {
        weatherInfoDTOHashMap.clear();
    }

    public List<String> getCitys() {
        List<String> citys = new ArrayList<>();
        citys.addAll(weatherInfoDTOHashMap.keySet());
        return citys;
    }

    public boolean isContains(String cityName) {
        for (String s : getCitys()) {
            if (s.equals(cityName)) return true;
        }
        return false;
    }

    public boolean remove(int position) {
        String cityName = getCitys().get(position);
        Iterator<String> iterator = getCitys().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(cityName)) {
                iterator.remove();
                weatherInfoDTOHashMap.remove(cityName);
                return true;
            }
        }
        return false;
    }
}
