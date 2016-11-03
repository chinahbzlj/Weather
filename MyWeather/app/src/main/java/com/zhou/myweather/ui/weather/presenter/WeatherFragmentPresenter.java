package com.zhou.myweather.ui.weather.presenter;

import com.zhou.myweather.model.mos.LocalCityInfoMO;

/**
 * Created by Zhou0618 on 2016/5/9 0009.
 */
public interface WeatherFragmentPresenter {
    void setCityData();
    void setTitle(int position);
    void queryCityWeather(String city);
    void queryCityWeather(String lat, String lon);
    void addCity(LocalCityInfoMO localCityInfoMO);
}
