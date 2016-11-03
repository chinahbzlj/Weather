package com.zhou.sdk.defines;

/**
 * Created by Powerbee on 2016/5/10.
 */
public class ApiMacro {
    /**
     * 天气预报
     * 目前先使用两个接口来查询城市天气，根据输入的城市名先查询城市id，然后再根据城市名和城市id来查询城市天气情况
     */
    /** 根据城市名查询城市id*/
    public static  final String URL_GET_AREAID = "https://route.showapi.com/9-3";
    /** 根据城市名和城市id查询城市天气*/
    public static final String URL_GET_WEATHER = "https://route.showapi.com/9-2";
    /** 根据百度地图坐标查询城市*/
    public static final String URL_GET_WEATHER_COORDINATE = "https://route.showapi.com/9-5";


}
