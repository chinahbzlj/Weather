package com.zhou.myweather.module.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhou.myweather.module.main.weather.CityWeatherFragment;

/**
 * Created by 周利杰 on 2017/7/25.
 */

public class CityWeatherAdapter extends FragmentPagerAdapter {
    private String[] citys;

    public CityWeatherAdapter(FragmentManager fm) {
        super(fm);
        citys = new String[0];
    }

    public void setCitys(String[] citys) {
        this.citys = citys;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return CityWeatherFragment.newInstance(citys[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return citys.length;
    }
}