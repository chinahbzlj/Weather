package com.zhou.myweather.model.dao;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.zhou.myweather.model.DatabaseHelper;
import com.zhou.myweather.model.mos.CityMO;
import com.zhou.myweather.util.LogUtil;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

public class CityDao extends BaseDao {

//    public static void addOrUpdate(CityMO cityMO){
//        if (cityMO == null)
//            return;
//        try {
//            DatabaseHelper.getDBHelper().getCityDao().createOrUpdate(cityMO);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static boolean addCity(CityMO cityMO) {
        try {
            DatabaseHelper.getDBHelper().getCityDao().createOrUpdate(cityMO);
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static List<CityMO> queryAllCity() {
        try {
            return DatabaseHelper.getDBHelper().getCityDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<CityMO> queryCity(String name) {
        List<CityMO> cityMOs = new ArrayList<>();
        try {
            cityMOs.addAll(DatabaseHelper.getDBHelper().getCityDao().queryBuilder().where().like("namecn", "%" + name + "%").query());
        } catch (SQLException e) {

            LogUtil.e("查询数据库错误信息", e.getMessage());
            e.printStackTrace();
        }
        return cityMOs;
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
    public static void addOrUpdateCity(List<CityMO> cMos) {
        if (cMos == null)
            return;
        AndroidDatabaseConnection connection = null;
        String pointName = "concentratorPointName";
        Savepoint savepoint = null;
        try {
            connection = new AndroidDatabaseConnection(DatabaseHelper.getDBHelper().getWritableDatabase(), true);
            DatabaseHelper.getDBHelper().getCityDao().setAutoCommit(connection, false);
            savepoint = connection.setSavePoint(pointName);
            for (CityMO cmo : cMos) {
                DatabaseHelper.getDBHelper().getCityDao().createOrUpdate(cmo);
            }
            connection.commit(savepoint);
            DatabaseHelper.getDBHelper().getCityDao().setAutoCommit(connection, true);

        } catch (SQLException e) {
            LogUtil.w("CityDAO", "addOrUpdateCity, write:" + e.getMessage());
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LogUtil.w("CityDAO", "addOrUpdateCity, rollback:" + e.getMessage());
            }
        }
    }


}
