package com.zhou.myweather.sdk.model.dto;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-14.
 */
public class AttributionDTO {
    public String area;
    public String areaid;
    public String distric;
    public String prov;
    public CityInfoDTO cityInfoDTO;

    public AttributionDTO(JSONObject data) {
        this.area = data.optString("area");
        this.areaid = data.optString("areaid");
        this.distric = data.optString("distric");
        this.prov = data.optString("distric");
        this.cityInfoDTO = new CityInfoDTO(data.optJSONObject("cityInfo"));
    }
}
