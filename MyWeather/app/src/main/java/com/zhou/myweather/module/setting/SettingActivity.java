package com.zhou.myweather.module.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;

/**
 * Created by 周利杰 on 2017/9/8.
 */

public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
//        titleTextView.setText("设置");l
        toolbar.setTitle("设置");
    }
}
    