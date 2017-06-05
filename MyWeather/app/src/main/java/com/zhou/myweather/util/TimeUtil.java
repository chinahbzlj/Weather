package com.zhou.myweather.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zhou on 2015-09-11.
 */
public class TimeUtil {

    /**
     * 获取天气预报所需要的时间     20150911105004
     */
    public static String getSystem(){
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(data);
    }

}
