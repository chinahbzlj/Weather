package com.zhou.sdk.model.dto;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-10.
 * 现在实时的天气情况
 */
public class NowStateDTO {
    /** 空气指数，越小越好 */
    public String aqi;
    /** aqi明细数据 */
    public AqiDetailDTO aqiDetailDTO;
    /** 空气湿度 */
    public String sd;
    /** 气温  */
    public String temperature;
    /** 获得气温的时间 */
    public String temperature_time;
    /** 天气 */
    public String weather;
    /** 天气小图标 */
    public String weather_pic;
    /** 风向 */
    public String wind_direction;
    /** 风力 */
    public String wind_power;

    public NowStateDTO(JSONObject data){
        this.aqi = data.optString("aqi");
        this.sd = data.optString("sd");
        this.temperature = data.optString("temperature");
        this.temperature_time = data.optString("temperature");
        this.weather = data.optString("weather");
        this.weather_pic = data.optString("weather_pic");
        this.wind_direction = data.optString("wind_direction");
        this.wind_power = data.optString("wind_power");
        this.aqiDetailDTO = new AqiDetailDTO(data.optJSONObject("aqiDetail"));
    }
}