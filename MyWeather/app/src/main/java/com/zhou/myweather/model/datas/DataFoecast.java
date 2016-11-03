package com.zhou.myweather.model.datas;

import com.zhou.myweather.model.mos.ForecastMO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Powerbee on 2016/6/15.
 */
public class DataFoecast {

    private Map<String,ForecastMO> forecastMOMap= new HashMap<>();

    public void setAllForecast(List<ForecastMO> forecastMOs){
        if(forecastMOs == null) return;
        forecastMOMap.clear();
        for(ForecastMO forecastMO : forecastMOs){
            this.forecastMOMap.put(forecastMO.name,forecastMO);
        }
    }

    public void addForecast(List<ForecastMO> forecastMOs){
        if(forecastMOs == null) return;
        for(ForecastMO forecastMO : forecastMOs){
            this.forecastMOMap.put(forecastMO.name,forecastMO);
        }
    }

    public void refreshForecast(List<ForecastMO> forecastMOs) {
        if (forecastMOs == null) return;
        for(ForecastMO forecastMO : forecastMOs){
            this.forecastMOMap.remove(forecastMO.name);
            this.forecastMOMap.put(forecastMO.name,forecastMO);
        }
    }
    public List<ForecastMO> getAllForecastForName(String name){
        List<ForecastMO> forecastMOs = new ArrayList<>();
        for(ForecastMO forecastMO : new ArrayList<>(forecastMOMap.values())){
            if(forecastMO.name.contains(name))
                forecastMOs.add(forecastMO);
        }
        Collections.sort(forecastMOs);
        return forecastMOs;
    }
}
