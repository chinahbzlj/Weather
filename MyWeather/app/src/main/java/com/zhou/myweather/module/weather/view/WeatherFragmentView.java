package com.zhou.myweather.module.weather.view;

import com.zhou.myweather.model.mos.LocalCityInfoMO;

import java.util.List;

/**
 * Created by Zhou0618 on 2016/5/9 0009.
 */
public interface WeatherFragmentView {

    public void setCitys(List<LocalCityInfoMO> citys);

    public void setTitle(String city);

    public void showLocationDialog();

    public void locationFinish(String msg);

    public void addCity(LocalCityInfoMO localCityInfoMO);

}
