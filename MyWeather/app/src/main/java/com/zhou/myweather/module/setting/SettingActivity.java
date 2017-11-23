package com.zhou.myweather.module.setting;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;

/**
 * Created by 周利杰 on 2017/9/8.
 */

public class SettingActivity extends BaseActivity {
    private TextView tvSettingVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
//        titleTextView.setText("设置");l
        toolbar.setTitle("设置");
        String version = "";
        try {
            version = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tvSettingVersion = (TextView) findViewById(R.id.tvSettingVersion);
        tvSettingVersion.setText("当前版本：" + version);
    }
}
