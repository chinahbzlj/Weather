package com.zhou.myweather.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 */
public class GlassDTO {
    /** 白天能见度差不需要佩戴太阳镜 */
    public String desc;
    /** 不需要 */
    public String title;
    public GlassDTO(JSONObject data){
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }

}
