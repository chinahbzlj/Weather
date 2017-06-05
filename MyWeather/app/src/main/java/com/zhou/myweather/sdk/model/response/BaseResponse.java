package com.zhou.myweather.sdk.model.response;

import com.zhou.myweather.sdk.defines.ErrorCode;
import com.zhou.myweather.sdk.defines.protocol.IServiceResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-08.
 */
public class BaseResponse implements IServiceResponse {
    public int errCode;
    public int ret_code;

    @Override
    public void initData(byte[] value) {
        String jsonStr = new String(value);
        jsonStr.replace("//", "");
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            errCode = (int) jsonObject.getInt("showapi_res_code");
            if (errCode == ErrorCode.SUCCESS) {
                paseData(jsonObject.get("showapi_res_body"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            errCode = (int) jsonObject.getInt("showapi_res_code");
            if (errCode == ErrorCode.SUCCESS) {
                paseData(jsonObject.get("showapi_res_body"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void paseData(Object data) throws JSONException {
        ret_code = ((JSONObject) data).getInt("ret_code");
    }

    @Override
    public boolean isError() {
        return errCode != ErrorCode.SUCCESS;
    }

    @Override
    public int errMsgRes() {
        return 0;
    }

    @Override
    public String errMsg() {
        return "";
    }

    @Override
    public int getErrorCode() {
        return 0;
    }
}