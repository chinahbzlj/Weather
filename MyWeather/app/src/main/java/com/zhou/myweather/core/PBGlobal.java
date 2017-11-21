package com.zhou.myweather.core;

import android.content.Context;


import com.zhou.myweather.sdk.defines.Config;

/**
 * Created by Powerbee on 2016/5/10.
 */
public class PBGlobal {
    public static boolean isOne = true;
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
    private static PBGlobal pbGlobal = null;
    private Context appContext;
    private PBGlobal() {
    }

    private static synchronized void syncInit() {
        if (pbGlobal == null) {
            pbGlobal = new PBGlobal();
        }
    }

    public static PBGlobal getPbGlobal() {
        if (pbGlobal == null)
            syncInit();
        return pbGlobal;
    }

    public void init(Context appContext) {
        this.appContext = appContext;
    }


    public void setAppContext(Context context) {
        this.appContext = context;
    }

    public Context getAppContext() {
        return this.appContext;
    }

}
