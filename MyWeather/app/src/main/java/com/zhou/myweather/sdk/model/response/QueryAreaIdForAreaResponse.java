package com.zhou.myweather.sdk.model.response;


import com.zhou.myweather.sdk.model.dto.AttributionDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou on 2015-09-08.
 */
public class QueryAreaIdForAreaResponse extends BaseResponse {
    public List<AttributionDTO> attributionDTOs;

    @Override
    protected void paseData(Object data) throws JSONException {
        super.paseData(data);

        if (data instanceof JSONObject) {
            attributionDTOs = new ArrayList<AttributionDTO>();
            JSONArray jsonArray = ((JSONObject) data).getJSONArray("list");
            for(int i = 0; i < jsonArray.length(); i++){
                attributionDTOs.add(new AttributionDTO(jsonArray.getJSONObject(i)));
            }
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