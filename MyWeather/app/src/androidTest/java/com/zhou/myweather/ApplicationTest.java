package com.zhou.myweather;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.zhou.myweather.util.DateUtil;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test() {
//        LogUtil.sysout("测试","...");
//        ExcelUtil.readCityExcelFile(FileManagerUtils.getFileRootPath()+"weather_areaid.xls");

//        CityDao.addOrUpdateCity(ExcelUtil.readCityExcelFile(FileManagerUtils.getFileRootPath() + "/weather_areaid.xls"));
//        List<CityMO> cityMOList = ExcelUtil.readCityExcelFile(FileManagerUtils.getFileRootPath() + "/weather_areaid.xls");
//        LogUtil.e("城市列表", " " + cityMOList.size());
////        CityDao.addOrUpdateCity(cityMOList);
//        CityDao.addOrUpdate(cityMOList.get(0));

        DateUtil.isRefresh("20160614100000");
    }
}