package com.zhou.myweather.sdk.model.request;

//import com.android.volley.Request;
import com.zhou.myweather.sdk.defines.ApiMacro;
import com.zhou.myweather.sdk.defines.protocol.IServiceRequest;

import java.util.Map;

/**
 * Created by zhou on 2015-09-08.
 */
public class BaseRequest implements IServiceRequest {
    protected String appId = "825";
    protected String secret = "";
    ApiMacro ApiMacro = new ApiMacro();

    @Override
    public String getURL() {
        return null;
    }

    @Override
    public boolean validateParams() {
        return true;
    }

    @Override
    public int getMethod() {
//        return Request.Method.GET;
        return 0;
    }

    @Override
    public Object getEntity() {
        return null;
    }

    @Override
    public Map<String, String> getHeader() {
        return null;
    }
}
