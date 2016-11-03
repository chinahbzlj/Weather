package com.zhou.sdk.model.response;


import com.zhou.sdk.model.dto.CityWeatherDTO;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-08.
 */
public class QueryWeatherResponse extends BaseResponse {
    public CityWeatherDTO cityWeatherDTO;
    @Override
    protected void paseData(Object data) throws JSONException {
        super.paseData(data);
        if (data instanceof JSONObject) {
            ((JSONObject)data).getInt("ret_code");
            cityWeatherDTO = new CityWeatherDTO(data);
        }
    }

    @Override
    public boolean isError() {
        return super.isError();
    }

    @Override
    public int errMsgRes() {
        return super.errMsgRes();
    }

    @Override
    public String errMsg() {
        return super.errMsg();
    }
}
