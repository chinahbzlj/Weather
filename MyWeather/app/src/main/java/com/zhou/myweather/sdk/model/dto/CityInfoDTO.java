package com.zhou.myweather.sdk.model.dto;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 查询的地区基本资料
 */
public class CityInfoDTO {
    /** 区域id  */
    public String c1;
    /** 城市级别 */
    public String c10;
    /** 城市区号 */
    public String c11;
    /** 邮编 */
    public String c12;
    /** 海拔 */
    public String c15;
    /** 雷达站号 */
    public String c16;
    /** */
    public String c17;
    /** 城市英文名 */
    public String c2;
    /** 城市中文名 */
    public String c3;
    /** 城市所在市英文名 */
    public String c4;
    /** 城市所在市中文名 */
    public String c5;
    /** 城市所在省英文名 */
    public String c6;
    /** 城市所在省中文名 */
    public String c7;
    /** 城市所在国家英文名 */
    public String c8;
    /** 城市所在国家中文名 */
    public String c9;
    /** 纬度 */
    public String latitude;
    /** 经度 */
    public String longitude;

    public CityInfoDTO(JSONObject data) {
        this.c1 = data.optString("c1");
        this.c2 = data.optString("c2");
        this.c3 = data.optString("c3");
        this.c4 = data.optString("c4");
        this.c5 = data.optString("c5");
        this.c6 = data.optString("c6");
        this.c7 = data.optString("c7");
        this.c8 = data.optString("c8");
        this.c9 = data.optString("c9");
        this.c10 = data.optString("c10");
        this.c11 = data.optString("c11");
        this.c12 = data.optString("c12");
        this.c15 = data.optString("c15");
        this.c16 = data.optString("c16");
        this.c17 = data.optString("c17");
        this.latitude = data.optString("latitude");
        this.longitude = data.optString("longitude");
    }
}
