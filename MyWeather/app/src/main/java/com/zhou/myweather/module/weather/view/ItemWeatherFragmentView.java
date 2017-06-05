package com.zhou.myweather.module.weather.view;

import com.zhou.myweather.model.mos.LocalCityInfoMO;

/**
 * Created by Powerbee on 2016/5/10.
 */
public interface ItemWeatherFragmentView {
    void refreshView(Object o);
    void stopRefresh();
    void startRefresh();
    LocalCityInfoMO getLocalCity();
}
