package com.zhou.myweather.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Powerbee on 2016/6/22.
 */
public class HotCityModel {
    public static List<String> getHotCitys() {
        List<String> citys = new ArrayList<>();
        citys.add("北京市");
        citys.add("天津市");
        citys.add("上海市");
        citys.add("重庆市");
        citys.add("沈阳市");
        citys.add("大连市");
        citys.add("长春市");
        citys.add("哈尔滨市");
        citys.add("郑州市");
        citys.add("武汉市");
        citys.add("长沙市");
        citys.add("广州市");
        citys.add("深圳市");
        citys.add("南京市");
        return citys;
    }
}
