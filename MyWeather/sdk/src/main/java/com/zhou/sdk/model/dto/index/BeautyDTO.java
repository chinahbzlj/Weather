package com.zhou.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 化妆指数
 */
public class BeautyDTO {
    /**
     * 请选用中性保湿型霜类化妆品。
     */
    public String desc;
    /**
     * 保湿
     */
    public String title;

    public BeautyDTO(JSONObject data) {
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }
}
