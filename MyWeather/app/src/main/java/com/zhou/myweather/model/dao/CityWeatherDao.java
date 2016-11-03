package com.zhou.myweather.model.dao;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.zhou.myweather.model.DatabaseHelper;
import com.zhou.myweather.model.mos.CityMO;
import com.zhou.myweather.model.mos.CityWeatherMO;
import com.zhou.sdk.util.LogUtil;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

public class CityWeatherDao extends BaseDao {


    public static boolean addCityWeather(CityWeatherMO cityWeatherMO) {
        try {
            DatabaseHelper.getDBHelper().getCityWeatherDao().createOrUpdate(cityWeatherMO);
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

//    public static boolean add(CityWeatherMO){
//
//    }
    public static List<CityWeatherMO> queryAllCity() {
        try {
            return DatabaseHelper.getDBHelper().getCityWeatherDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static CityWeatherMO queryCityForName(String name){
//        try {
//            return DatabaseHelper.getDBHelper().getCityWeatherDao().queryBuilder().where().eq("name",name).queryForFirst();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static List<CityWeatherMO> queryCity(String name) {
        List<CityWeatherMO> cityWeatherMOs = new ArrayList<>();
        try {
            cityWeatherMOs.addAll(DatabaseHelper.getDBHelper().getCityWeatherDao().queryBuilder().where().like("namecn", "%" + name + "%").query());
        } catch (SQLException e) {

            LogUtil.e("查询数据库错误信息", e.getMessage());
            e.printStackTrace();
        }
        return cityWeatherMOs;
    }

    public static List<CityMO> queryCityForName(String name) {
        try {
            return DatabaseHelper.getDBHelper().getCityDao().queryBuilder().where().eq("namecn", name).query();
        } catch (SQLException e) {
            LogUtil.e("查询数据库错误信息", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //向表格中插入数据
    public static void addOrUpdateCity(List<CityWeatherMO> cityWeatherMOs) {
        if (cityWeatherMOs == null)
            return;
        AndroidDatabaseConnection connection = null;
        String pointName = "concentratorPointName";
        Savepoint savepoint = null;
        try {
            connection = new AndroidDatabaseConnection(DatabaseHelper.getDBHelper().getWritableDatabase(), true);
            DatabaseHelper.getDBHelper().getCityDao().setAutoCommit(connection, false);
            savepoint = connection.setSavePoint(pointName);
            for (CityWeatherMO cmo : cityWeatherMOs) {
                DatabaseHelper.getDBHelper().getCityWeatherDao().createOrUpdate(cmo);
            }
            connection.commit(savepoint);
            DatabaseHelper.getDBHelper().getCityWeatherDao().setAutoCommit(connection, true);

        } catch (SQLException e) {
            LogUtil.w("CityDAO", "addOrUpdateCity, write:" + e.getMessage());
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LogUtil.w("CityDAO", "addOrUpdateCity, rollback:" + e.getMessage());
            }
        }
    }


    public static CityWeatherMO queryAllCityForName(String c3) {
        try {
            return DatabaseHelper.getDBHelper().getCityWeatherDao().queryBuilder().where().eq("name", c3).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
