package com.zhou.myweather.module.main;

import android.text.TextUtils;

import com.zhou.myweather.db.WeatherDAO;
import com.zhou.myweather.db.WeatherVO;
import com.zhou.myweather.db.dto.ForecastPO;
import com.zhou.myweather.db.dto.WeatherPO;
import com.zhou.myweather.model.WeatherInfoManager;
import com.zhou.myweather.util.LogcatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周利杰 on 17-11-21.
 */

public class Main4Persenter implements MainContract.Persenter {
    private MainContract.View view;
    private List<String> citys;

    public Main4Persenter(MainContract.View view) {
        this.view = view;
        this.view.setPersenter(this);
        this.citys = new ArrayList<>();
    }

    @Override
    public void start() {
        view.setCitys(citys);
        if (WeatherInfoManager.getWeatherInfoManager().getCitys().size() == 0) {
            List<WeatherPO> weatherPO = WeatherDAO.getWeatherDAO().getDaoSession().getWeatherPODao().queryBuilder().list();
            if (weatherPO == null || weatherPO.size() == 0) {
                view.addCity();
                return;
            } else {
                for (WeatherPO po : weatherPO) {
                    List<ForecastPO> forecastPOS = WeatherDAO.getWeatherDAO().getForecastOPS(po.city_name);
                    WeatherVO weatherVO = new WeatherVO(po, forecastPOS);
                    WeatherInfoManager.getWeatherInfoManager().addCityWeather(weatherVO);
                }
            }
        }
        citys.addAll(WeatherInfoManager.getWeatherInfoManager().getCitys());
        view.notifyAdapter();
        if (citys.size() == 0) view.addCity();
        else view.setTitle(citys.get(0));
    }

    @Override
    public void setPosition(int position) {
        view.setTitle(citys.get(position));
    }

    @Override
    public void detach() {

    }

    @Override
    public void addCity(String cityName) {
        if (!TextUtils.isEmpty(cityName)) citys.add(cityName);
        else LogcatUtil.d("没有选择城市");
        view.setTitle(cityName);
        view.notifyAdapter();
        WeatherInfoManager.getWeatherInfoManager().addCity(cityName);
        view.setCurrentItem(WeatherInfoManager.getWeatherInfoManager().getCitys().size());
    }

    @Override
    public void removeCity(int position) {
        if (position == -1) return;
        citys.remove(position);
        WeatherInfoManager.getWeatherInfoManager().remove(position);
        view.notifyAdapter();
        view.setCurrentItem(position);
        if (position == 0) view.setTitle("");
        else view.setTitle(citys.get(position - 1));
    }

}
