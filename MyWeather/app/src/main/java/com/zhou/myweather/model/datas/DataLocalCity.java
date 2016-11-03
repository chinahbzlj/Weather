package com.zhou.myweather.model.datas;

import com.zhou.myweather.model.mos.LocalCityInfoMO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Powerbee on 2016/6/12.
 */
public class DataLocalCity {
    /**
     * 保存本地城市信息
     */
    private Map<String, LocalCityInfoMO> citysMap = new HashMap<>();


    /**
     * 设置本地城市信息
     *
     * @param cityInfoMOs
     */
    public void setAllLocalCitys(List<LocalCityInfoMO> cityInfoMOs) {
        if (cityInfoMOs == null) return;
        this.citysMap.clear();
        for (LocalCityInfoMO localCityInfoMO : cityInfoMOs) {
            this.citysMap.put(localCityInfoMO.c3, localCityInfoMO);
        }
    }

    public Map<String, LocalCityInfoMO> getAllLocalCitys() {
        return citysMap;
    }

    public List<LocalCityInfoMO> getAllLocalCity() {
        List<LocalCityInfoMO> cityInfoMOs = new ArrayList<>(this.citysMap.values());
        Collections.sort(cityInfoMOs);
        return cityInfoMOs;
    }

    /**
     * 添加城市
     *
     * @param localCityInfoMO
     */
    public void addLocalCity(LocalCityInfoMO localCityInfoMO) {
        if (localCityInfoMO == null) return;
        this.citysMap.put(localCityInfoMO.c3, localCityInfoMO);
    }

    /**
     * 删除城市
     *
     * @param localCityInfoMO
     */
    public void deleteLocalCity(LocalCityInfoMO localCityInfoMO) {
        if (localCityInfoMO == null) return;
        this.citysMap.remove(localCityInfoMO.c3);
    }

    /**
     * 批量删除城市
     *
     * @param localCityInfoMOs
     */
    public void deleteLocalCitys(List<LocalCityInfoMO> localCityInfoMOs) {
        if (localCityInfoMOs == null) return;
        for (int i = 0; i < localCityInfoMOs.size(); i++) {
            this.citysMap.remove(localCityInfoMOs.get(i).c3);
        }
    }

    /**
     * 查询城市
     *
     * @param localCityInfoMO
     * @return
     */
    public LocalCityInfoMO queryLocalCity(LocalCityInfoMO localCityInfoMO) {
        if (localCityInfoMO == null) return null;
        return this.citysMap.get(localCityInfoMO.c3);
    }
}
