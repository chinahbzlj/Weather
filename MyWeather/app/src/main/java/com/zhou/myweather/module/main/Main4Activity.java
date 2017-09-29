package com.zhou.myweather.module.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.module.main.adapter.CityWeatherAdapter;
import com.zhou.myweather.module.setting.SettingActivity;
import com.zhou.myweather.module.settingcity.SettingCityActivity;
import com.zhou.myweather.util.ToastUtil;

public class Main4Activity extends AppCompatActivity implements MainContract.View {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TextView titleTextView;
    private String[] citys = {"松江", "北京", "广州"};
    private CityWeatherAdapter adapter;

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
        adapter = new CityWeatherAdapter(getSupportFragmentManager());
        adapter.setCitys(citys);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(citys[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        titleTextView = (TextView) findViewById(R.id.title);
        if (citys.length != 0)
            titleTextView.setText(citys[0]);
        else {

        }
    }

    private void setTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
//            ToastUtil.getInstance().toastShowS("设置城市");
            startActivity(new Intent(this, SettingCityActivity.class));
        } else if (id == R.id.action_settings) {
//            ToastUtil.getInstance().toastShowS("设置");
            startActivity(new Intent(this, SettingActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPersenter(MainContract.Persenter persenter) {

    }


}
