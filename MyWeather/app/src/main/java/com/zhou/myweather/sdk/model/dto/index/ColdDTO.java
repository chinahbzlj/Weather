package com.zhou.myweather.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 感冒
 */
public class ColdDTO {

    /**
     * 天较凉，增加衣服，注意防护。
     */
    public String desc;
    /**
     * 较易发
     */
    public String title;

    public ColdDTO(JSONObject data) {
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }
}
