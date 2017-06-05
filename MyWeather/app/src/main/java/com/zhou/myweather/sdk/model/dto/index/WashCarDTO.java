package com.zhou.myweather.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 */
public class WashCarDTO {
    /** 有雨，雨水和泥水会弄脏爱车。 */
    public String desc;
    /** 不宜 */
    public String title;

    public WashCarDTO(JSONObject data){
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }
}