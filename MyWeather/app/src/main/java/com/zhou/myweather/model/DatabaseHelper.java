package com.zhou.myweather.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zhou.myweather.core.PBGlobal;
import com.zhou.myweather.model.mos.CityMO;
import com.zhou.myweather.model.mos.CityWeatherMO;
import com.zhou.myweather.model.mos.ForecastMO;
import com.zhou.myweather.model.mos.LocalCityInfoMO;
import com.zhou.myweather.util.LogUtil;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    // name of the database file for your application -- change to something
    // appropriate for your app
    private static final String DATABASE_NAME = "weather.db";
    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_VERSION = 10;
    // the DAO object we use to access the SimpleData table
    private Dao<LocalCityInfoMO, String> cityInfoDao = null; // 城市表
    private Dao<CityMO, String> cityDao = null;
    private Dao<CityWeatherMO, String> cityWeatherDao = null;
    private Dao<ForecastMO, String> forecastDao = null;
    private static DatabaseHelper databaseHelper;
    private Context mContext;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public static void initDataBase(Context context) {

        databaseHelper = new DatabaseHelper(context);

    }

    public static DatabaseHelper getDBHelper() {
        if (databaseHelper == null && PBGlobal.getPbGlobal().getAppContext() != null) {
            databaseHelper = new DatabaseHelper(PBGlobal.getPbGlobal().getAppContext());
        }
        if (databaseHelper == null) {
            LogUtil.e(DatabaseHelper.class.getName(), "请调用initDataBase()方法初始化数据库");
        }

        return databaseHelper;
    }

    /**
     * This is called when the database is first created. Usually you should
     * call createTable statements here to create the tables that will store
     * your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            LogUtil.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, LocalCityInfoMO.class);
            TableUtils.createTable(connectionSource, CityMO.class);
            TableUtils.createTable(connectionSource, CityWeatherMO.class);
            TableUtils.createTable(connectionSource, ForecastMO.class);
        } catch (SQLException e) {
            LogUtil.e(DatabaseHelper.class.getName(), "Can't create database: " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher
     * version number. This allows you to adjust the various data to match the
     * new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            LogUtil.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, LocalCityInfoMO.class, true);
            TableUtils.dropTable(connectionSource, CityMO.class, true);
            TableUtils.dropTable(connectionSource, CityWeatherMO.class, true);
            TableUtils.dropTable(connectionSource, ForecastMO.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            LogUtil.e(DatabaseHelper.class.getName(), "Can't drop databases: "
                    + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It
     * will create it or just give the cached value.
     */
    public Dao<LocalCityInfoMO, String> getCityinfoDao() throws SQLException {
        if (cityInfoDao == null) {
            cityInfoDao = getDao(LocalCityInfoMO.class);
        }
        return cityInfoDao;
    }


    public Dao<CityMO, String> getCityDao() throws SQLException {
        if (cityDao == null) {
            cityDao = getDao(CityMO.class);
        }
        return cityDao;
    }

    public Dao<CityWeatherMO, String> getCityWeatherDao() throws SQLException {
        if (cityWeatherDao == null) {
            cityWeatherDao = getDao(CityWeatherMO.class);
        }
        return cityWeatherDao;
    }

    public Dao<ForecastMO, String> getForecastMODao() throws SQLException {
        if (forecastDao == null) {
            forecastDao = getDao(ForecastMO.class);
        }
        return forecastDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        cityInfoDao = null;
        cityDao = null;
        cityWeatherDao = null;
        forecastDao = null;
    }
}
