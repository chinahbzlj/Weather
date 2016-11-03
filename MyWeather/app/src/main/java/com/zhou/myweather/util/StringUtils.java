package com.zhou.myweather.util;

/**
 * Created by Powerbee on 2016/4/28.
 */
public class StringUtils {


    public static String getWeek(String weekday) {
        if (weekday.equalsIgnoreCase("1")) {
            return "星期一";
        } else if (weekday.equalsIgnoreCase("2")) {
            return "星期二";
        } else if (weekday.equalsIgnoreCase("3")) {
            return "星期三";
        } else if (weekday.equalsIgnoreCase("4")) {
            return "星期四";
        } else if (weekday.equalsIgnoreCase("5")) {
            return "星期五";
        } else if (weekday.equalsIgnoreCase("6")) {
            return "星期六";
        } else if (weekday.equalsIgnoreCase("7")) {
            return "星期日";
        }
        return "星期八";
    }

    //    05:10|18:32
    public static String getSunBegin(String time) {
        return time.substring(0, time.indexOf("|"));
    }

    public static String getSunEnd(String time) {
        return time.substring(time.indexOf("|") + 1);
    }


    public static String getArea(String area) {
        if (area.substring(area.length() - 1).equalsIgnoreCase("区")) {
            return area.substring(0, area.length() - 1);
        }
        return area;
    }
}
