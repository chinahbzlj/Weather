package com.zhou.myweather.module.city;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.model.WeatherInfoManager;
import com.zhou.myweather.util.ToastUtil;

/**
 * Created by 周利杰 on 2017/9/8.
 */

public class ManageCityActivity extends BaseActivity implements ManageCityContract.View {
    private static final String TAG = "ManageCityActivity";
    private ManageCityContract.Persenter persenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_city);
        new ManageCityPersenter(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ManageAdapter(WeatherInfoManager.getWeatherInfoManager().getWeatherMap()));
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

    @Override
    public void setPersenter(ManageCityContract.Persenter persenter) {
        this.persenter = persenter;
    }
}
