package com.zhou.myweather.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 紫外线
 */
public class UvDTO {
    /** 辐射弱，涂擦SPF8-12防晒护肤品。 */
    public String desc;
    /** 最弱 */
    public String title;
    public UvDTO(JSONObject data){
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }
}