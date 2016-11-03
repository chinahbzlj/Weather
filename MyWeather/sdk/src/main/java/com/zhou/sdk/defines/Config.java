package com.zhou.sdk.defines;

public class Config {
    public static boolean isDebug = true;
    public static String ON = "1";
    public static String OFF = "0";
    /**
     * 是否需要返回七天数据中的后四天 0表示不返回 1表示返回
     */
    public static String needMoreDay = Config.ON;
    /**
     * 是否需要返回指数数据 0表示不返回 1表示返回
     */
    public static String needIndex = Config.OFF;
    /**
     * 是否需要每小时数据的累积数组 因为半小时刷一次实时状态，所以数组的最大长度为48，每天0点长度初始化为0。 0表示不返回 1表示返回
     */
    public static String needHourData = Config.OFF;
}