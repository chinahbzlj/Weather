package com.zhou.myweather.sdk.model.request;


import com.zhou.myweather.util.TimeUtil;

/**
 * Created by zhou on 2015-09-08.
 */
public class QueryAreaIdForAreaRequest extends BaseRequest {
    private String area;
    private String time;

    public QueryAreaIdForAreaRequest(String area) {
        this.area = area;
        this.time = TimeUtil.getSystem();
    }

    @Override
    public int getMethod() {
        return super.getMethod();
    }

    @Override
    public String getURL() {
        return ApiMacro.URL_GET_AREAID;
    }

    @Override
    public Object getEntity() {
        return "?area=" + area
                + "&showapi_appid=" + appId + "&showapi_sign=" + secret
                + "&showapi_timestamp=" + time;
    }
}