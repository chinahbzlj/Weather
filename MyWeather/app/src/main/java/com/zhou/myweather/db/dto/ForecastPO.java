package com.zhou.myweather.db.dto;


import com.zhou.myweather.sdk.defines.Config;
import com.zhou.myweather.sdk.model.dto.IndexInfoDTO;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.json.JSONObject;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhou on 2015-09-09.
 */
@Entity
public class ForecastPO {
    public String name;

    /**
     * 星期几
     */
    public String weekday;

    /**
     * 白天天气温度
     */
    public String day_air_temperature;


    /**
     * 晚上天气温度
     */
    public String night_air_temperature;

    /**  */
    public String day_weather_pic;

    @Generated(hash = 1262054627)
    public ForecastPO(String name, String weekday, String day_air_temperature,
            String night_air_temperature, String day_weather_pic) {
        this.name = name;
        this.weekday = weekday;
        this.day_air_temperature = day_air_temperature;
        this.night_air_temperature = night_air_temperature;
        this.day_weather_pic = day_weather_pic;
    }

    @Generated(hash = 593178146)
    public ForecastPO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeekday() {
        return this.weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getDay_air_temperature() {
        return this.day_air_temperature;
    }

    public void setDay_air_temperature(String day_air_temperature) {
        this.day_air_temperature = day_air_temperature;
    }

    public String getNight_air_temperature() {
        return this.night_air_temperature;
    }

    public void setNight_air_temperature(String night_air_temperature) {
        this.night_air_temperature = night_air_temperature;
    }

    public String getDay_weather_pic() {
        return this.day_weather_pic;
    }

    public void setDay_weather_pic(String day_weather_pic) {
        this.day_weather_pic = day_weather_pic;
    }


}