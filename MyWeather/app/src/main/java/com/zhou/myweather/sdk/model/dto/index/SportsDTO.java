package com.zhou.myweather.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 运动
 */
public class SportsDTO {
    /** 有较强降水，请在室内进行休闲运动。 */
    public String desc;
    /** 较不宜 */
    public String title;
    public SportsDTO(JSONObject data){
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }
}
