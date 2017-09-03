package com.zhou.myweather.util.http;

import com.zhou.myweather.net.CityAllWeatherInfoDTO;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by powerbee-z on 16-10-11.
 */
public interface RequestServers {

    //根据城市名或者城市id查询天气
    @GET("9-2")
    Observable<CityAllWeatherInfoDTO> getWeatherForArea(
            @Query("showapi_sign") String showapi_sign,
            @Query("showapi_appid") String showapi_appid,
            @Query("showapi_timestamp") String showapi_timestamp,
            @Query("areaid") String areaid,
            @Query("area") String area,
            @Query("needMoreDay") String needMoreDay,
            @Query("needIndex") String needIndex,
            @Query("needHourData") String needHourData,
            @Query("need3HourForcast") String need3HourForcast,
            @Query("needAlarm") String needAlarm
    );
}
