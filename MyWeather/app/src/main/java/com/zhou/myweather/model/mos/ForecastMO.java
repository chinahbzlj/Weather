package com.zhou.myweather.model.mos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zhou.myweather.sdk.model.dto.ForecastDTO;

/**
 * Created by Powerbee on 2016/5/23.
 */

@DatabaseTable(tableName = "ForecastTable")
public class ForecastMO extends BaseMO implements Comparable<ForecastMO> {

    /**
     * 星期几
     */
    @DatabaseField(id = true)
    public String name;
    @DatabaseField
    public String weekday;
    @DatabaseField
    /**
     * 年月日
     */
    public String day;
    /**
     * 白天天气温度
     */
    @DatabaseField
    public String day_air_temperature;
    /**
     * 白天天气
     */
    @DatabaseField
    public String day_weather;
    /**  */
    @DatabaseField
    public String day_weather_pic;
    /**
     * 白天 风向编号
     */
    @DatabaseField
    public String day_wind_direction;
    /**
     * 白天风力编号
     */
    @DatabaseField
    public String day_wind_power;
    /**
     * 晚上天气温度
     */
    @DatabaseField
    public String night_air_temperature;
    /**
     * 晚上天气
     */
    @DatabaseField
    public String night_weather;
    /**  */
    @DatabaseField
    public String night_weather_pic;
    /**
     * 晚上风向编号
     */
    @DatabaseField
    public String night_wind_direction;
    /**
     * 晚上风力编号
     */
    @DatabaseField
    public String night_wind_power;
    /**
     * 日出日落时间
     */
    @DatabaseField
    public String sun_begin_end;
    @DatabaseField
    public String jiangshui;
    @DatabaseField
    public String air_press;
    @DatabaseField
    public String ziwaixian;
    @DatabaseField
    public Integer order;


    public ForecastMO() {
    }

    public ForecastMO(ForecastDTO forecastDTO, String name) {
        this.name = name + forecastDTO.name;
        this.day = forecastDTO.day;
        this.day_air_temperature = forecastDTO.day_air_temperature;
        this.day_weather = forecastDTO.day_weather;
        this.day_weather_pic = forecastDTO.day_weather_pic;
        this.day_wind_direction = forecastDTO.day_wind_direction;
        this.day_wind_power = forecastDTO.day_wind_power;
        this.night_air_temperature = forecastDTO.night_air_temperature;
        this.night_weather = forecastDTO.night_weather;
        this.night_weather_pic = forecastDTO.night_weather_pic;
        this.night_wind_direction = forecastDTO.night_wind_direction;
        this.night_wind_power = forecastDTO.night_wind_power;
        this.sun_begin_end = forecastDTO.sun_begin_end;
        this.weekday = forecastDTO.weekday;
        this.jiangshui = forecastDTO.jiangshui;
        this.air_press = forecastDTO.air_press;
        this.ziwaixian = forecastDTO.ziwaixian;
        this.order = forecastDTO.order;
    }


    @Override
    public int compareTo(ForecastMO another) {
        return this.order.compareTo(another.order);
    }
}
