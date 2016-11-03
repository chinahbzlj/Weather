package com.zhou.sdk.model.request;


import com.zhou.sdk.defines.Config;
import com.zhou.sdk.util.TimeUtil;

/**
 * Created by zhou on 2015-09-08.
 */
public class QueryWeatherRequest extends BaseRequest {
    private String area;
    private String areaId;
    private String needHourData;
    private String needIndex;
    private String needMoreDay;
    private String time;

    public QueryWeatherRequest(String area, String areaId) {
        this.area = area;
        this.areaId = areaId;
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
        return ApiMacro.URL_GET_WEATHER;
    }

    @Override
    public Object getEntity() {
        return "?area=" + area + "&areaid=" + areaId
                + "&needHourData=" + needHourData + "&needIndex=" + needIndex + "&needMoreDay=" + needMoreDay
                + "&showapi_appid=" + appId + "&showapi_sign=" + secret
                + "&showapi_timestamp=" + time;
    }
}