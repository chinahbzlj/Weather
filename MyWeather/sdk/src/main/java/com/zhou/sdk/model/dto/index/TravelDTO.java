package com.zhou.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 旅游
 */
public class TravelDTO {
    /** 有较强降雨，不便出行，建议带上雨具。 */
    public String desc;
    /** 一般 */
    public String title;
    public TravelDTO(JSONObject data){
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }
}
