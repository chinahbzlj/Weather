package com.zhou.myweather.module.weather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.zhou.myweather.base.TabBaseFragment;

import java.util.List;
import java.util.Map;

/**
 * Created by Powerbee on 2016/5/6.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter {


    private List<TabBaseFragment> fragments;
    private Map<String, Object> fragmentMap;
    private List<Map<String, Object>> fragmentList;
//    public MyFragmentAdapter(FragmentManager fm, List<TabBaseFragment> fragments) {
//        super(fm);
//        this.fragments = fragments;
//    }

    public MyFragmentAdapter(FragmentManager fm, Map<String, Object> fragmentMap) {
        super(fm);
        this.fragmentMap = fragmentMap;
    }

    public MyFragmentAdapter(FragmentManager fm, List<Map<String, Object>> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return (TabBaseFragment) fragmentList.get(position).get("fragment");
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    //    public void setData(List<TabBaseFragment> fragments) {
//        this.fragments = fragments;
//        this.notifyDataSetChanged();
//    }
    public void setData(List<Map<String,Object>> fragmentList) {
        this.fragmentList = fragmentList;
        this.notifyDataSetChanged();
    }
}

