package com.zhou.sdk.model.request;


import com.zhou.sdk.defines.Config;
import com.zhou.sdk.util.TimeUtil;

/**
 * Created by zhou on 2015-09-08.
 */
public class QueryWeatherForCoordinateRequest extends BaseRequest {
    private String from;
    private String lat;
    private String lng;
    private String needHourData;
    private String needIndex;
    private String needMoreDay;
    private String time;

    public QueryWeatherForCoordinateRequest(String lat, String lng) {
        this.from = 5 + "";
        this.lat = lat;
        this.lng = lng;
        this.needHourData = Config.needHourData;
        this.needIndex = Config.needIndex;
        this.needMoreDay = Config.needMoreDay;
        this.time = TimeUtil.getSystem();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getURL() {
        return ApiMacro.URL_GET_WEATHER_COORDINATE;
    }

    @Override
    public Object getEntity() {
        return "?from=" + from + "&lat=" + lat + "&lng=" + lng
                + "&needHourData=" + needHourData + "&needIndex=" + needIndex + "&needMoreDay=" + needMoreDay
                + "&showapi_appid=" + appId + "&showapi_sign=" + secret
                + "&showapi_timestamp=" + time;
    }
}