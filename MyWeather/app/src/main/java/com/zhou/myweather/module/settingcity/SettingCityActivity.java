package com.zhou.myweather.module.settingcity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.util.ToastUtil;

/**
 * Created by 周利杰 on 2017/9/8.
 */

public class SettingCityActivity extends BaseActivity {
    private static final String TAG = "SettingCityActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_city);
        toolbar.setTitle("管理城市");
//        titleTextView.setText("设置城市");t
        findViewById(R.id.add_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().toastShowS("添加城市");
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.getInstance().toastShowS("桌面天气");
            }
        });
    }
}
