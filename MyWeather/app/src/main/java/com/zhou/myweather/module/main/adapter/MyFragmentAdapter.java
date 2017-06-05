package com.zhou.myweather.module.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhou.myweather.base.TabBaseFragment;

import java.util.List;
import java.util.Map;

/**
 * Created by Powerbee on 2016/5/6.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {


    private List<Map<String, Object>> fragmentList;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return (TabBaseFragment) fragmentList.get(position).get("fragment");
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void setData(List<Map<String, Object>> fragmentList) {
        this.fragmentList = fragmentList;

    }
}

