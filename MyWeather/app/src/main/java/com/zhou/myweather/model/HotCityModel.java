package com.zhou.myweather.model;

import com.zhou.myweather.util.LogcatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Powerbee on 2016/6/22.
 */
public class HotCityModel {
    public static List<String> getHotCitys() {
        List<String> citys = new ArrayList<String>();
        citys.add("当前位置");
        citys.add("北京");
        citys.add("上海");
        citys.add("广州");
        citys.add("深圳");
        citys.add("天津");
        citys.add("武汉");
        citys.add("沈阳");
        citys.add("重庆");
        citys.add("杭州");
        citys.add("南京");
        citys.add("哈尔滨");
        citys.add("长春");
        citys.add("呼和浩特");
        citys.add("石家庄");
        citys.add("银川");
        citys.add("乌鲁木齐");
        citys.add("拉萨");
        citys.add("西宁");
        citys.add("西安");
        citys.add("兰州");
        citys.add("太原");
        citys.add("昆明");
        citys.add("南宁");
        citys.add("成都");
        citys.add("长沙");
        citys.add("济南");
        citys.add("南昌");
        citys.add("合肥");
        citys.add("郑州");
        citys.add("福州");
        citys.add("贵阳");
        citys.add("海口");
        citys.add("秦皇岛");
        citys.add("桂林");
        citys.add("三亚");
        citys.add("香港");
//        citys.add("澳门");
        int num = 3 - citys.size() % 3;
        if (num != 0) {
            for (int i = 0; i < num; i++) {
                citys.add(" ");
            }
        }
        return citys;
    }
}
