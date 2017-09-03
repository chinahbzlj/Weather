package com.zhou.myweather.module.main.weather;

import com.zhou.myweather.net.CityAllWeatherInfoDTO;

/**
 * Created by 周利杰 on 2017/9/3.
 */

public class WeatherPOJO {
    public String city_weather;
    public String city_weather_temperature;
    public String week;
    public String highestTemperatures;
    public String lowestTemperature;
    public String city_weather_info;
    public String city_sunup;
    public String city_sunset;
    public String temperature;
    public String humidity;
    public String wind_power;
    public String wind_direction;
    public String aqi;
    public String primary_pollutant;
    public String quality;
    public String pm2_5;


    public WeatherPOJO(CityAllWeatherInfoDTO weatherDTO) {
        this.city_weather = weatherDTO.showapi_res_body.now.weather;
        this.city_weather_temperature = weatherDTO.showapi_res_body.now.temperature;
        this.week = weatherDTO.showapi_res_body.f1.weekday;
        this.highestTemperatures = weatherDTO.showapi_res_body.f1.day_air_temperature;
        this.lowestTemperature = weatherDTO.showapi_res_body.f1.night_air_temperature;
        this.city_weather_info = "今天" + weatherDTO.showapi_res_body.now.weather + "," + weatherDTO.showapi_res_body.f1.day_wind_direction + " "
                + weatherDTO.showapi_res_body.f1.day_wind_power + "。最高气温" + weatherDTO.showapi_res_body.f1.day_air_temperature + "。今晚"
                + weatherDTO.showapi_res_body.f1.night_weather + ",最低气温" + weatherDTO.showapi_res_body.f1.night_air_temperature;
        this.city_sunup = weatherDTO.showapi_res_body.f1.sun_begin_end.substring(0, weatherDTO.showapi_res_body.f1.sun_begin_end.indexOf("|"));
        this.city_sunset = weatherDTO.showapi_res_body.f1.sun_begin_end.substring(weatherDTO.showapi_res_body.f1.sun_begin_end.indexOf("|") + 1);
//        this.temperature = weatherDTO.showapi_res_body.now.temperature;
        this.humidity = weatherDTO.showapi_res_body.now.sd;
        this.wind_power = weatherDTO.showapi_res_body.now.wind_power;
        this.wind_direction = weatherDTO.showapi_res_body.now.wind_direction;
        this.aqi = weatherDTO.showapi_res_body.now.aqi;
        this.primary_pollutant = weatherDTO.showapi_res_body.now.aqiDetail.primary_pollutant;
        this.quality = weatherDTO.showapi_res_body.now.aqiDetail.quality;
        this.pm2_5 = weatherDTO.showapi_res_body.now.aqiDetail.pm2_5;
    }
}
