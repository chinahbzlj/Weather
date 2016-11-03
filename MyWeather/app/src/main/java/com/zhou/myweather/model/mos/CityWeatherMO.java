package com.zhou.myweather.model.mos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zhou.sdk.model.dto.CityWeatherHolder;
import com.zhou.sdk.model.dto.ForecastDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Powerbee on 2016/5/23.
 */

@DatabaseTable(tableName = "CityWeatherTable")
public class CityWeatherMO extends BaseMO {
    @DatabaseField(id = true)
    public String name;
    @DatabaseField
    public String time;
    @DatabaseField
    public String temperature;
    @DatabaseField
    public String weather;
    @DatabaseField
    public String day_wind_direction;
    @DatabaseField
    public String day_wind_power;
    @DatabaseField
    public String day_air_temperature;
    @DatabaseField
    public String night_weather;
    @DatabaseField
    public String night_air_temperature;
    @DatabaseField
    public String weekday;
    @DatabaseField
    public String sun_begin_end;
    @DatabaseField
    public String sd;
    @DatabaseField
    public String wind_power;
    @DatabaseField
    public String aqi;
    @DatabaseField
    public String primary_pollutant;
    @DatabaseField
    public String quality;
    @DatabaseField
    public String pm2_5;
    @DatabaseField
    public String wind_direction;

    public List<ForecastMO> forecastMOs = new ArrayList<>();

    public CityWeatherMO() {
    }

    public CityWeatherMO(CityWeatherHolder cityWeatherHolder, String name) {
        this.name = name;
        this.temperature = cityWeatherHolder.temperature;
        this.weather = cityWeatherHolder.weather;
        this.day_wind_direction = cityWeatherHolder.day_wind_direction;
        this.day_wind_power = cityWeatherHolder.day_wind_power;
        this.day_air_temperature = cityWeatherHolder.day_air_temperature;
        this.night_weather = cityWeatherHolder.night_weather;
        this.night_air_temperature = cityWeatherHolder.night_air_temperature;
        this.weekday = cityWeatherHolder.weekday;
        this.sun_begin_end = cityWeatherHolder.sun_begin_end;
        this.sd = cityWeatherHolder.sd;
        this.wind_power = cityWeatherHolder.wind_power;
        this.aqi = cityWeatherHolder.aqi;
        this.primary_pollutant = cityWeatherHolder.primary_pollutant;
        this.quality = cityWeatherHolder.quality;
        this.pm2_5 = cityWeatherHolder.pm2_5;
        this.wind_direction = cityWeatherHolder.wind_direction;
        this.time = cityWeatherHolder.time;
        for (ForecastDTO forecastDTO : cityWeatherHolder.list) {
            forecastMOs.add(new ForecastMO(forecastDTO, this.name));
        }
    }
}
