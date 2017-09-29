package com.zhou.myweather.module.settingcity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;

/**
 * Created by 周利杰 on 2017/9/8.
 */

public class SettingCityActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_city);
        toolbar.setTitle("管理城市");
//        titleTextView.setText("设置城市");
    }
}
