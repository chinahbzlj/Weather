package com.zhou.myweather.model;

import java.util.List;
import java.util.Map;

/**
 * Created by 周利杰 on 2017/9/30.
 */

public class CityManager {
    private static final CityManager ourInstance = new CityManager();

    public static CityManager getInstance() {
        return ourInstance;
    }

//    private List<String> cityList=
    private CityManager() {
    }
}
