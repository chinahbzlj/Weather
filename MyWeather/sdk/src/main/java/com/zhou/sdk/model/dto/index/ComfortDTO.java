package com.zhou.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 舒适指数
 */
public class ComfortDTO {
    /** 白天不冷不热，风力不大。 */
    public String desc;
    /** 舒适 */
    public String title;
    public ComfortDTO(JSONObject data){
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }
}
