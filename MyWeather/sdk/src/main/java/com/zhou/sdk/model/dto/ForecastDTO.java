package com.zhou.sdk.model.dto;


import com.zhou.sdk.defines.Config;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 */
public class ForecastDTO {
    public String name;
    /**
     * 年月日
     */
    public String day;
    /**
     * 白天天气温度
     */
    public String day_air_temperature;
    /**
     * 白天天气
     */
    public String day_weather;
    /**  */
    public String day_weather_pic;
    /**
     * 白天 风向编号
     */
    public String day_wind_direction;
    /**
     * 白天风力编号
     */
    public String day_wind_power;
    /**
     * 晚上天气温度
     */
    public String night_air_temperature;
    /**
     * 晚上天气
     */
    public String night_weather;
    /**  */
    public String night_weather_pic;
    /**
     * 晚上风向编号
     */
    public String night_wind_direction;
    /**
     * 晚上风力编号
     */
    public String night_wind_power;
    /**
     * 日出日落时间
     */
    public String sun_begin_end;
    /**
     * 星期几
     */
    public String weekday;
    public String jiangshui;
    public String air_press;
    public String ziwaixian;
    public IndexInfoDTO index;
    public int order;

    public ForecastDTO(JSONObject data) {
        this.day = data.optString("day");
        this.day_air_temperature = data.optString("day_air_temperature");
        this.day_weather = data.optString("day_weather");
        this.day_weather_pic = data.optString("day_weather_pic");
        this.day_wind_direction = data.optString("day_wind_direction");
        this.day_wind_power = data.optString("day_wind_power");
        this.night_air_temperature = data.optString("night_air_temperature");
        this.night_weather = data.optString("night_weather");
        this.night_weather_pic = data.optString("night_weather_pic");
        this.night_wind_direction = data.optString("night_wind_direction");
        this.night_wind_power = data.optString("night_wind_power");
        this.sun_begin_end = data.optString("sun_begin_end");
        this.weekday = data.optString("weekday");
        this.jiangshui = data.optString("jiangshui");
        this.air_press = data.optString("air_press");
        this.ziwaixian = data.optString("ziwaixian");
        if (Config.needIndex.equals(Config.ON)) {
            this.index = new IndexInfoDTO(data.optJSONObject("index"));
        }
        this.name = name;
    }

    public ForecastDTO(JSONObject data, String name) {
        this.day = data.optString("day");
        this.day_air_temperature = data.optString("day_air_temperature");
        this.day_weather = data.optString("day_weather");
        this.day_weather_pic = data.optString("day_weather_pic");
        this.day_wind_direction = data.optString("day_wind_direction");
        this.day_wind_power = data.optString("day_wind_power");
        this.night_air_temperature = data.optString("night_air_temperature");
        this.night_weather = data.optString("night_weather");
        this.night_weather_pic = data.optString("night_weather_pic");
        this.night_wind_direction = data.optString("night_wind_direction");
        this.night_wind_power = data.optString("night_wind_power");
        this.sun_begin_end = data.optString("sun_begin_end");
        this.weekday = data.optString("weekday");
        this.jiangshui = data.optString("jiangshui");
        this.air_press = data.optString("air_press");
        this.ziwaixian = data.optString("ziwaixian");
        if (Config.needIndex.equals(Config.ON)) {
            this.index = new IndexInfoDTO(data.optJSONObject("index"));
        }
        this.name = name;
        this.order = Integer.parseInt(name.substring(1));
    }

    public ForecastDTO(JSONObject data, String name, int order) {
        this.day = data.optString("day");
        this.day_air_temperature = data.optString("day_air_temperature");
        this.day_weather = data.optString("day_weather");
        this.day_weather_pic = data.optString("day_weather_pic");
        this.day_wind_direction = data.optString("day_wind_direction");
        this.day_wind_power = data.optString("day_wind_power");
        this.night_air_temperature = data.optString("night_air_temperature");
        this.night_weather = data.optString("night_weather");
        this.night_weather_pic = data.optString("night_weather_pic");
        this.night_wind_direction = data.optString("night_wind_direction");
        this.night_wind_power = data.optString("night_wind_power");
        this.sun_begin_end = data.optString("sun_begin_end");
        this.weekday = data.optString("weekday");
        this.jiangshui = data.optString("jiangshui");
        this.air_press = data.optString("air_press");
        this.ziwaixian = data.optString("ziwaixian");
        if (Config.needIndex.equals(Config.ON)) {
            this.index = new IndexInfoDTO(data.optJSONObject("index"));
        }
        this.name = name;
        this.order = order;
    }
}