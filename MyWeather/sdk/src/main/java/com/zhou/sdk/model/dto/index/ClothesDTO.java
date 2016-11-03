package com.zhou.sdk.model.dto.index;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 穿衣指数
 */
public class ClothesDTO {
    /**
     * 建议穿薄外套或牛仔裤等服装。
     */
    public String desc;
    /**
     * 较舒适
     */
    public String title;

    public ClothesDTO(JSONObject data) {
        this.desc = data.optString("desc");
        this.title = data.optString("title");
    }
}
