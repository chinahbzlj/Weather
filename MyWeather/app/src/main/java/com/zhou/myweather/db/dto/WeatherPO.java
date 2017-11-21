package com.zhou.myweather.db.dto;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 周利杰 on 17-11-20.
 */
@Entity
public class WeatherPO {
    public String city_name;
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
    public String time;
    public String localTime;
    @Generated(hash = 605773172)
    public WeatherPO(String city_name, String city_weather,
            String city_weather_temperature, String week,
            String highestTemperatures, String lowestTemperature,
            String city_weather_info, String city_sunup, String city_sunset,
            String temperature, String humidity, String wind_power,
            String wind_direction, String aqi, String primary_pollutant,
            String quality, String pm2_5, String time, String localTime) {
        this.city_name = city_name;
        this.city_weather = city_weather;
        this.city_weather_temperature = city_weather_temperature;
        this.week = week;
        this.highestTemperatures = highestTemperatures;
        this.lowestTemperature = lowestTemperature;
        this.city_weather_info = city_weather_info;
        this.city_sunup = city_sunup;
        this.city_sunset = city_sunset;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind_power = wind_power;
        this.wind_direction = wind_direction;
        this.aqi = aqi;
        this.primary_pollutant = primary_pollutant;
        this.quality = quality;
        this.pm2_5 = pm2_5;
        this.time = time;
        this.localTime = localTime;
    }
    @Generated(hash = 1297177695)
    public WeatherPO() {
    }
    public String getCity_name() {
        return this.city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    public String getCity_weather() {
        return this.city_weather;
    }
    public void setCity_weather(String city_weather) {
        this.city_weather = city_weather;
    }
    public String getCity_weather_temperature() {
        return this.city_weather_temperature;
    }
    public void setCity_weather_temperature(String city_weather_temperature) {
        this.city_weather_temperature = city_weather_temperature;
    }
    public String getWeek() {
        return this.week;
    }
    public void setWeek(String week) {
        this.week = week;
    }
    public String getHighestTemperatures() {
        return this.highestTemperatures;
    }
    public void setHighestTemperatures(String highestTemperatures) {
        this.highestTemperatures = highestTemperatures;
    }
    public String getLowestTemperature() {
        return this.lowestTemperature;
    }
    public void setLowestTemperature(String lowestTemperature) {
        this.lowestTemperature = lowestTemperature;
    }
    public String getCity_weather_info() {
        return this.city_weather_info;
    }
    public void setCity_weather_info(String city_weather_info) {
        this.city_weather_info = city_weather_info;
    }
    public String getCity_sunup() {
        return this.city_sunup;
    }
    public void setCity_sunup(String city_sunup) {
        this.city_sunup = city_sunup;
    }
    public String getCity_sunset() {
        return this.city_sunset;
    }
    public void setCity_sunset(String city_sunset) {
        this.city_sunset = city_sunset;
    }
    public String getTemperature() {
        return this.temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getHumidity() {
        return this.humidity;
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getWind_power() {
        return this.wind_power;
    }
    public void setWind_power(String wind_power) {
        this.wind_power = wind_power;
    }
    public String getWind_direction() {
        return this.wind_direction;
    }
    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }
    public String getAqi() {
        return this.aqi;
    }
    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
    public String getPrimary_pollutant() {
        return this.primary_pollutant;
    }
    public void setPrimary_pollutant(String primary_pollutant) {
        this.primary_pollutant = primary_pollutant;
    }
    public String getQuality() {
        return this.quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public String getPm2_5() {
        return this.pm2_5;
    }
    public void setPm2_5(String pm2_5) {
        this.pm2_5 = pm2_5;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getLocalTime() {
        return this.localTime;
    }
    public void setLocalTime(String localTime) {
        this.localTime = localTime;
    }


}
