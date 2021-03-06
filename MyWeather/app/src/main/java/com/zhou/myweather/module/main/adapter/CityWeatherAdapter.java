package com.zhou.myweather.module.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.zhou.myweather.module.main.weather.CityWeatherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周利杰 on 2017/7/25.
 */

public class CityWeatherAdapter extends FragmentStatePagerAdapter {
    private List<String> citys = new ArrayList<>();

    public CityWeatherAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setCitys(List<String> citys) {
        this.citys = citys;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return CityWeatherFragment.newInstance(citys.get(position));
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return citys.size();
    }
}