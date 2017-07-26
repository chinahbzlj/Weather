package com.zhou.myweather.module.main.weather;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhou.myweather.R;

/**
 * Created by 周利杰 on 2017/7/25.
 */

public class CityWeatherFragment extends Fragment {

    public CityWeatherFragment() {
    }

    public static CityWeatherFragment newInstance(String city) {
        CityWeatherFragment fragment = new CityWeatherFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_weather, container, false);
        return view;
    }
}
