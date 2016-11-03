package com.zhou.sdk.model.dto;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-10.
 */
public class AqiDetailDTO {
    /** 空气质量指数 越小越好  */
    public String aqi;
    /** 地区 */
    public String area;
    /**  */
    public String area_code;
    /** 一氧化碳 */
    public String co;
    /** 二氧化氮 */
    public String no2;
    /** 臭氧1小时平均 */
    public String o3;
    /** 臭氧8小时平均 */
    public String o3_8h;
    /** 颗粒物（粒径小于等于10μm）1小时平均 */
    public String pm10;
    /** 颗粒物（粒径小于等于2.5μm）1小时平均 */
    public String pm2_5;
    /** 首要污染物 */
    public String primary_pollutant;
    /** 空气质量指数类别，有“优、良、轻度污染、中度污染、重度污染、严重污染”6类 */
    public String quality;
    /** 二氧化硫1小时平均 */
    public String so2;
    
    public AqiDetailDTO(JSONObject data){
        this.aqi = data.optString("aqi");
        this.area = data.optString("area");
        this.area_code = data.optString("area_code");
        this.co = data.optString("co");
        this.no2 = data.optString("no2");
        this.o3 = data.optString("o3");
        this.o3_8h = data.optString("o3_8h");
        this.pm10 = data.optString("pm10");
        this.pm2_5 = data.optString("pm2_5");
        this.primary_pollutant = data.optString("primary_pollutant");
        this.quality = data.optString("quality");
        this.so2 = data.optString("so2");
    }
}
