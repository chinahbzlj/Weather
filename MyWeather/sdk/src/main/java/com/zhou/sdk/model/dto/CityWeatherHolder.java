package com.zhou.sdk.model.dto;

import java.util.List;

/**
 * Created by Powerbee on 2016/6/8.
 */
public class CityWeatherHolder {
    public List<ForecastDTO> list;
    public String temperature;
    public String weather;
    public String day_wind_direction;
    public String day_wind_power;
    public String day_air_temperature;
    public String night_weather;
    public String night_air_temperature;
    public String weekday;
    public String sun_begin_end;
    public String sd;
    public String wind_power;
    public String aqi;
    public String primary_pollutant;
    public String quality;
    public String pm2_5;
    public String wind_direction;
    public String time;

    public CityWeatherHolder(List<ForecastDTO> list, String temperature, String weather, String dayWindDirection, String day_wind_power, String day_air_temperature, String night_weather, String night_air_temperature, String weekday,
                             String sun_begin_end, String sd, String wind_power, String aqi, String primary_pollutant, String quality, String pm2_5, String wind_direction, String time) {
        this.list = list;
        this.temperature = temperature;
        this.weather = weather;
        this.day_wind_direction = dayWindDirection;
        this.day_wind_power = day_wind_power;
        this.day_air_temperature = day_air_temperature;
        this.night_weather = night_weather;
        this.night_air_temperature = night_air_temperature;
        this.weekday = weekday;
        this.sun_begin_end = sun_begin_end;
        this.sd = sd;
        this.wind_power = wind_power;
        this.aqi = aqi;
        this.primary_pollutant = primary_pollutant;
        this.quality = quality;
        this.pm2_5 = pm2_5;
        this.wind_direction = wind_direction;
        this.time = time;
    }
}
