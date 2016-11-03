package com.zhou.myweather.util;

import com.zhou.myweather.model.mos.CityMO;
import com.zhou.sdk.util.LogUtil;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by Powerbee on 2016/5/23.
 */
public class ExcelUtil {
    private static final String TAG = "ExcelUtil";

    public static List<CityMO> readCityExcelFile(InputStream inputStream) {
        if(inputStream == null){
            LogUtil.e("ExcelUtil","读取文件出错");
            return null;
        }
        try {
            Workbook wb = Workbook.getWorkbook(inputStream);
            Sheet sheet = wb.getSheet(0);
            int colNum = sheet.getColumns();// 获取列数
            int rowNum = sheet.getRows();// 获取行数
            LogUtil.sysout(TAG, "列数" + colNum + " 行数" + rowNum);
            if (colNum != 9) {
                LogUtil.w(TAG, "文件格式不对，列数小于3");
                return null;
            }
            if (rowNum <= 1) {
                LogUtil.w(TAG, "文件中没有数据");
                return null;
            }
            List<CityMO> excleInfos = new ArrayList<CityMO>();
            for (int i = 1; i < rowNum; i++) {
                Cell col0 = sheet.getCell(0, i);
                Cell col1 = sheet.getCell(1, i);
                Cell col2 = sheet.getCell(2, i);
                Cell col3 = sheet.getCell(3, i);
                Cell col4 = sheet.getCell(4, i);
                Cell col5 = sheet.getCell(5, i);
                Cell col6 = sheet.getCell(6, i);
                Cell col7 = sheet.getCell(7, i);
                Cell col8 = sheet.getCell(8, i);
                CityMO cityMO = new CityMO(col0.getContents(), col1.getContents(), col2.getContents(), col3.getContents(), col4.getContents(), col5.getContents(), col6.getContents(), col7.getContents(), col8.getContents());
                excleInfos.add(cityMO);
//                if (TextUtils.isEmpty(col2.getContents()))// 如果id为空，则这一行肯定没有数据，不需要入库
//                    continue;
//                ExcelInfo excleInfo = null;
//                if (colNum > 4 && !TextUtils.isEmpty(sheet.getCell(4, i).getContents())) {
//                    excleInfo = new ExcelInfo(col0.getContents(), col1.getContents(), col2.getContents(), col3.getContents(), sheet
//                            .getCell(4, i).getContents());
//                    excleInfos.add(excleInfo);
//                } else {
//                    excleInfo = new ExcelInfo(col0.getContents(), col1.getContents(), col2.getContents(), col3.getContents());
//                    excleInfos.add(excleInfo);
//                }
            }
            return excleInfos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 读取excel文件
    public static List<CityMO> readCityExcelFile(String path) {
        try {
            LogUtil.e("文件路径", path);
            File xls = new File(path);
            if (!xls.exists()) {
                LogUtil.w(TAG, "文件不存在");
                return null;
            }
            Workbook wb = Workbook.getWorkbook(xls);
            Sheet sheet = wb.getSheet(0);
            int colNum = sheet.getColumns();// 获取列数
            int rowNum = sheet.getRows();// 获取行数
            LogUtil.sysout(TAG, "列数" + colNum + " 行数" + rowNum);
            if (colNum != 9) {
                LogUtil.w(TAG, "文件格式不对，列数小于3");
                return null;
            }
            if (rowNum <= 1) {
                LogUtil.w(TAG, "文件中没有数据");
                return null;
            }
            List<CityMO> excleInfos = new ArrayList<CityMO>();
            for (int i = 1; i < rowNum; i++) {
                Cell col0 = sheet.getCell(0, i);
                Cell col1 = sheet.getCell(1, i);
                Cell col2 = sheet.getCell(2, i);
                Cell col3 = sheet.getCell(3, i);
                Cell col4 = sheet.getCell(4, i);
                Cell col5 = sheet.getCell(5, i);
                Cell col6 = sheet.getCell(6, i);
                Cell col7 = sheet.getCell(7, i);
                Cell col8 = sheet.getCell(8, i);
                CityMO cityMO = new CityMO(col0.getContents(), col1.getContents(), col2.getContents(), col3.getContents(), col4.getContents(), col5.getContents(), col6.getContents(), col7.getContents(), col8.getContents());
                excleInfos.add(cityMO);
//                if (TextUtils.isEmpty(col2.getContents()))// 如果id为空，则这一行肯定没有数据，不需要入库
//                    continue;
//                ExcelInfo excleInfo = null;
//                if (colNum > 4 && !TextUtils.isEmpty(sheet.getCell(4, i).getContents())) {
//                    excleInfo = new ExcelInfo(col0.getContents(), col1.getContents(), col2.getContents(), col3.getContents(), sheet
//                            .getCell(4, i).getContents());
//                    excleInfos.add(excleInfo);
//                } else {
//                    excleInfo = new ExcelInfo(col0.getContents(), col1.getContents(), col2.getContents(), col3.getContents());
//                    excleInfos.add(excleInfo);
//                }
            }
            return excleInfos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
