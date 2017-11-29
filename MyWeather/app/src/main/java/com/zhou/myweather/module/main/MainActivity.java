package com.zhou.myweather.module.main;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;
import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.module.city.ManageCityActivity;
import com.zhou.myweather.module.main.adapter.CityWeatherAdapter;
import com.zhou.myweather.module.setting.SettingActivity;
import com.zhou.myweather.module.city.AddCityActivity;
import com.zhou.myweather.util.ActivityUtils;
import com.zhou.myweather.util.LogcatUtil;
import com.zhou.myweather.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements MainContract.View, CityManagerListener {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TextView titleTextView;
    private CityWeatherAdapter adapter;
    private MainContract.Persenter persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initView();
        new MainPersenter(this);
        persenter.start();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationIcon(R.drawable.icon_city_white2);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
//        WeatherInfoManager.getWeatherInfoManager().addCity("上海");
        adapter = new CityWeatherAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                persenter.setPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        titleTextView = (TextView) findViewById(R.id.title);

        //监听删除城市的动作。
        CityManagerListenerManager.getCityManagerListenerManager().setCityManagerListener(this);
    }

    /**
     * 跳转到添加城市界面
     */
    @Override
    public void addCity() {
        startActivityForResult(new Intent(this, AddCityActivity.class), ADD_CITY);
    }

    @Override
    public void notifyAdapter() {
//        if (adapter != null)
        adapter.notifyDataSetChanged();
//        else LogcatUtil.d("没有初始化");
    }

    @Override
    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void setCitys(List<String> citys) {
        adapter.setCitys(citys);
    }


    @Override
    public void setCurrentItem(int item) {
        mViewPager.setCurrentItem(item, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main4, menu);
        return true;
    }

    public static final int ADD_CITY = 1001;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivityForResult(new Intent(this, ManageCityActivity.class), ADD_CITY);
        } else if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class));
        }
        return true;
    }

    @Override
    public void setPersenter(MainContract.Persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogcatUtil.d(resultCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == MainActivity.ADD_CITY && data != null) {
                String cityName = data.getStringExtra(AddCityActivity.CITY_NAME);
                if (!TextUtils.isEmpty(cityName)) persenter.addCity(cityName);
                String position = data.getStringExtra("item");
                if (!TextUtils.isEmpty(position)) setCurrentItem(Integer.parseInt(position));
            }
        }
    }

    @Override
    public void removeCity(int position) {
        persenter.removeCity(position);
    }

    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtil.getInstance().toastShowS("再按一次退出应用");
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else ActivityUtils.getActivityUtils().exit();
    }



    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
