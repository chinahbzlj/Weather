package com.zhou.myweather.model.dao;

import com.zhou.myweather.model.DatabaseHelper;
import com.zhou.myweather.model.mos.LocalCityInfoMO;

import java.sql.SQLException;
import java.util.List;

public class CityInfoDao extends BaseDao {

    //城市信息
    public static void addOrUpdate(LocalCityInfoMO cityInfoMO) {
        if (cityInfoMO == null) {
            return;
        }
        try {
            DatabaseHelper.getDBHelper().getCityinfoDao().createOrUpdate(cityInfoMO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<LocalCityInfoMO> queryAllCity() {
        try {
            return DatabaseHelper.getDBHelper().getCityinfoDao().queryBuilder().orderBy("addTime",false).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteCitys(List<LocalCityInfoMO> cityInfoMOs){
        try {
            DatabaseHelper.getDBHelper().getCityinfoDao().delete(cityInfoMOs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
