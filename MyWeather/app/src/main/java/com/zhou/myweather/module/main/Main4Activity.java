package com.zhou.myweather.module.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.model.CityManager;
import com.zhou.myweather.model.CityModel;
import com.zhou.myweather.model.WeatherInfoManager;
import com.zhou.myweather.module.city.ManageCityActivity;
import com.zhou.myweather.module.main.adapter.CityWeatherAdapter;
import com.zhou.myweather.module.main.weather.CityWeatherFragment;
import com.zhou.myweather.module.setting.SettingActivity;
import com.zhou.myweather.module.weather.AddCityActivity;
import com.zhou.myweather.util.ActivityUtils;
import com.zhou.myweather.util.LogcatUtil;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class Main4Activity extends BaseActivity implements MainContract.View {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TextView titleTextView;
    private CityWeatherAdapter adapter;
    private List<String> citys;
    private List<CityWeatherFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
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
                Main4Activity.this.position = position;
                setTitle(citys.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        titleTextView = (TextView) findViewById(R.id.title);
        if (citys == null || citys.size() == 0)
            startActivityForResult(new Intent(this, AddCityActivity.class), ADD_CITY);
        else setTitle(citys.get(0));
    }

    private int position = -1;

    private void setTitle(String title) {
        titleTextView.setText(title);
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
//            ToastUtil.getInstance().toastShowS("设置城市");
//            startActivity(new Intent(this, ManageCityActivity.class));
            startActivityForResult(new Intent(this, ManageCityActivity.class), ADD_CITY);
        } else if (id == R.id.action_settings) {
//            ToastUtil.getInstance().toastShowS("设置");
            startActivity(new Intent(this, SettingActivity.class));
        }

        return true;
    }

    @Override
    public void setPersenter(MainContract.Persenter persenter) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogcatUtil.d(resultCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == Main4Activity.ADD_CITY && data != null) {
                String cityName = data.getStringExtra(AddCityActivity.CITY_NAME);
                if (!TextUtils.isEmpty(cityName)) {
                    setTitle(cityName);
                    WeatherInfoManager.getWeatherInfoManager().addCity(cityName);
                    citys = WeatherInfoManager.getWeatherInfoManager().getCitys();
                    adapter.setCitys(citys);
                    adapter.notifyDataSetChanged();
                    mViewPager.setCurrentItem(WeatherInfoManager.getWeatherInfoManager().getCitys().size(), false);
                }
            } else {

                citys = WeatherInfoManager.getWeatherInfoManager().getCitys();
                if (citys.size() > position && position != -1) {
                    setTitle(citys.get(position));
                } else {
                    position = citys.size() - 1;
                    setTitle(citys.get(position));
                }
                adapter.setCitys(citys);
                adapter.notifyDataSetChanged();
                mViewPager.setCurrentItem(position, false);
            }
        }
    }
}
