package com.zhou.myweather.module.welcome;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.core.MyApplication;
import com.zhou.myweather.db.WeatherDAO;
import com.zhou.myweather.db.dto.CityPO;
import com.zhou.myweather.module.main.MainActivity;
import com.zhou.myweather.util.ExcelUtil;
import com.zhou.myweather.util.LogcatUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周利杰 on 17-11-22.
 */

public class WelcomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
//去掉状态栏
        Window window = getWindow();
        window.addFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //隐藏虚拟按键
        //只是暂时隐藏，当用户产生交互行为(点击屏幕)时，虚拟按键还是会显示出来
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        WeatherDAO.getWeatherDAO().
//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(option);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        if (WeatherDAO.getWeatherDAO().getDaoSession().getCityPODao().count() != 2565)
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        LogcatUtil.d("1");
                        List<CityPO> cityPOS = ExcelUtil.readCityExcelFile(MyApplication.getInstance().getAssets().open("weather_areaid.xls"));
                        LogcatUtil.d("2");
                        WeatherDAO.getWeatherDAO().insertCity(cityPOS);
                        LogcatUtil.d("3");
                        handler.sendEmptyMessage(1001);
//                        ToastUtil.getInstance().post(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        else handler.sendEmptyMessage(1001);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LogcatUtil.d("4");
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        }
    };


}
