package com.zhou.myweather.module.main.weather;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.net.WeatherDTO;
import com.zhou.myweather.sdk.model.dto.NowStateDTO;
import com.zhou.myweather.widget.ViewPagerFragment;

/**
 * Created by 周利杰 on 2017/7/25.
 */

public class CityWeatherFragment extends ViewPagerFragment implements WeatherContract.View {
    private WeatherContract.Persenter persenter;
    private String mCity;
    private TextView city_weather_temperature, week, highestTemperatures, lowestTemperature, city_weather_info;
    private TextView city_sunup, city_sunset, temperature, humidity, wind_power, wind_direction, aqi, primary_pollutant, quality, pm2_5;

    public CityWeatherFragment() {
    }

    public static CityWeatherFragment newInstance(String city) {
        CityWeatherFragment fragment = new CityWeatherFragment();
        fragment.mCity = city;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(R.layout.fragment_item_weather2, container, false);
        new WeatherPersenter(this, mCity);
        return rootView;
    }

    @Override
    public void setPersenter(WeatherContract.Persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) persenter.getWeather();
    }

    @Override
    public void showWeather(WeatherDTO weatherDTO) {
        NowStateDTO nowStateDTO = weatherDTO.showapi_res_body.now;
        temperature.setText();
    }

    @Override
    public void onPause() {
        super.onPause();
        persenter.detach();
    }
}
