package com.zhou.myweather.module.main.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.net.CityAllWeatherInfoDTO;
import com.zhou.myweather.sdk.model.dto.NowStateDTO;
import com.zhou.myweather.widget.ViewPagerFragment;

/**
 * Created by 周利杰 on 2017/7/25.
 */

public class CityWeatherFragment extends ViewPagerFragment implements WeatherContract.View {
    private WeatherContract.Persenter persenter;
    private String mCity;
    private TextView city_weather, city_weather_temperature, week, highestTemperatures, lowestTemperature, city_weather_info;
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
        this.city_weather = (TextView) rootView.findViewById(R.id.city_weather);
        this.city_weather_temperature = (TextView) rootView.findViewById(R.id.city_weather_temperature);
        this.week = (TextView) rootView.findViewById(R.id.week);
        this.city_weather_info = (TextView) rootView.findViewById(R.id.city_weather_info);
        this.highestTemperatures = (TextView) rootView.findViewById(R.id.highestTemperatures);
        this.lowestTemperature = (TextView) rootView.findViewById(R.id.lowestTemperature);
        this.city_sunup = (TextView) rootView.findViewById(R.id.city_sunup);
        this.city_sunset = (TextView) rootView.findViewById(R.id.city_sunset);
        this.temperature = (TextView) rootView.findViewById(R.id.temperature);
        this.humidity = (TextView) rootView.findViewById(R.id.humidity);
        this.wind_power = (TextView) rootView.findViewById(R.id.wind_power);
        this.wind_direction = (TextView) rootView.findViewById(R.id.wind_direction);
        this.aqi = (TextView) rootView.findViewById(R.id.aqi);
        this.primary_pollutant = (TextView) rootView.findViewById(R.id.primary_pollutant);
        this.quality = (TextView) rootView.findViewById(R.id.quality);
        this.pm2_5 = (TextView) rootView.findViewById(R.id.pm2_5);
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
        else persenter.detach();
    }

    @Override
    public void showWeather(WeatherPOJO weatherPOJO) {
        this.city_weather.setText(weatherPOJO.city_weather);
        this.city_weather_temperature.setText(weatherPOJO.city_weather_temperature);
        this.week.setText(weatherPOJO.week);
        this.highestTemperatures.setText(weatherPOJO.highestTemperatures);
        this.lowestTemperature.setText(weatherPOJO.lowestTemperature);
        this.city_weather_info.setText(weatherPOJO.city_weather_info);
        this.city_sunup.setText(weatherPOJO.city_sunup);
        this.city_sunset.setText(weatherPOJO.city_sunset);
//        this.temperature.setText(weatherPOJO.temperature);
        this.humidity.setText(weatherPOJO.humidity);
        this.wind_power.setText(weatherPOJO.wind_power);
        this.wind_direction.setText(weatherPOJO.wind_direction);
        this.aqi.setText(weatherPOJO.aqi);
        this.primary_pollutant.setText(weatherPOJO.primary_pollutant);
        this.quality.setText(weatherPOJO.quality);
        this.pm2_5.setText(weatherPOJO.pm2_5);
    }

    @Override
    public void onPause() {
        super.onPause();
        persenter.detach();
    }
}
