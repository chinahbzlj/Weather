package com.zhou.myweather.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zhou on 2015-09-11.
 */
public class TimeUtil {

    /**
     * 获取天气预报所需要的时间     20150911105004
     */
    public static String getSystem() {
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(data);
    }

    public static boolean needRefresh(String newTime, String oldTime) {
        SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat oldSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        long min = 0;
        try {
            Date newDate = newSimpleDateFormat.parse(newTime);
            Date oldDate = oldSimpleDateFormat.parse(oldTime);
            long diff = newDate.getTime() - oldDate.getTime();
            long nm = 1000 * 60;
            min = diff / nm;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return min > 30 ? true : false;
    }

    public static boolean needRefresh(String newTime, String oldTime, String cityName, String city) {
        SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat oldSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        long min = 0;
//        try {
//            LogcatUtil.d(newSimpleDateFormat.parse(newTime).toString() + " " + oldSimpleDateFormat.parse(oldTime).toString() + " " + cityName + " " + city);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        try {
            Date newDate = newSimpleDateFormat.parse(newTime);
            Date oldDate = oldSimpleDateFormat.parse(oldTime);
            long diff = newDate.getTime() - oldDate.getTime();
            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 * 60;
            long nm = 1000 * 60;
            min = diff % nd % nh / nm;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            LogcatUtil.d(simpleDateFormat.format(newDate) + " " + simpleDateFormat.format(oldDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogcatUtil.d("时间差" + min);
        return min > 60 ? true : false;
    }
}
