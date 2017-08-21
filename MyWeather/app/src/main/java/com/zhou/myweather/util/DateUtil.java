package com.zhou.myweather.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Powerbee on 2016/6/13.
 */
public class DateUtil {
    @SuppressLint("WrongConstant")
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    @SuppressLint("WrongConstant")
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 根据当前时间和数据库中存储的天气时间判断是否需要更新
     *
     * @param time 20160608113000
     * @return
     */
    public static boolean isRefresh(String time) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            long l1 = dateFormat.parse(time).getTime();
            String t2 = TimeUtil.getSystem();
            long l2 = dateFormat.parse(t2).getTime();
            long diff = l2 - l1;
            if (diff / (1000 * 60) > 30)
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
