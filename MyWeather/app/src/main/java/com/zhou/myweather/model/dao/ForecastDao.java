package com.zhou.myweather.model.dao;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.zhou.myweather.model.DatabaseHelper;
import com.zhou.myweather.model.mos.ForecastMO;
import com.zhou.myweather.util.LogUtil;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

public class ForecastDao extends BaseDao {

    //城市信息
    public static void addOrUpdate(ForecastMO forecastMO) {
        if (forecastMO == null) {
            return;
        }
        try {
            DatabaseHelper.getDBHelper().getForecastMODao().createOrUpdate(forecastMO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ForecastMO> queryAllCity(){
        try {
            return DatabaseHelper.getDBHelper().getForecastMODao().queryBuilder().query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<ForecastMO> queryAllCityForName(String name) {
        try {
//            return DatabaseHelper.getDBHelper().getForecastMODao().queryBuilder().where().eq("name",name).query();
            return DatabaseHelper.getDBHelper().getForecastMODao().queryBuilder().where().like("name", "%" + name + "%").query();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //向表格中插入数据
    public static void addOrUpdateCity(List<ForecastMO> forecastMOs) {
        if (forecastMOs == null)
            return;
        AndroidDatabaseConnection connection = null;
        String pointName = "forecastMOsName";
        Savepoint savepoint = null;
        try {
            connection = new AndroidDatabaseConnection(DatabaseHelper.getDBHelper().getWritableDatabase(), true);
            DatabaseHelper.getDBHelper().getForecastMODao().setAutoCommit(connection, false);
            savepoint = connection.setSavePoint(pointName);
            for (ForecastMO cmo : forecastMOs) {
                DatabaseHelper.getDBHelper().getForecastMODao().createOrUpdate(cmo);
            }
            connection.commit(savepoint);
            DatabaseHelper.getDBHelper().getForecastMODao().setAutoCommit(connection, true);

        } catch (SQLException e) {
            LogUtil.w("ForecastDao", "addOrUpdateCity, write:" + e.getMessage());
            try {
                connection.rollback(savepoint);
            } catch (SQLException e1) {
                LogUtil.w("ForecastDao", "addOrUpdateCity, rollback:" + e.getMessage());
            }
        }
    }

    public static void add(List<ForecastMO> forecastMOs) {
//        DatabaseHelper.getDBHelper().getForecastMODao().
    }
}
