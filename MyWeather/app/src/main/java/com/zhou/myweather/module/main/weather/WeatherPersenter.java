package com.zhou.myweather.module.main.weather;

import com.zhou.myweather.module.main.weather.WeatherContract.Persenter;

/**
 * Created by 周利杰 on 2017/7/26.
 */

public class WeatherPersenter implements Persenter {
    private WeatherContract.View view;
//    private CompositeSubscriptio
    public WeatherPersenter(WeatherContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {

    }
}
