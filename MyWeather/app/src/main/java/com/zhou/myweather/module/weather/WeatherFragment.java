package com.zhou.myweather.module.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.TabBaseFragment;
import com.zhou.myweather.model.mos.LocalCityInfoMO;
import com.zhou.myweather.module.TestActivity;
import com.zhou.myweather.module.main.MyViewPager;
import com.zhou.myweather.module.weather.adapter.MyFragmentAdapter;
import com.zhou.myweather.module.weather.presenter.WeatherFragmentPresenterImpl;
import com.zhou.myweather.module.weather.view.WeatherFragmentView;
import com.zhou.myweather.util.LeHandler;
import com.zhou.myweather.util.TimeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Powerbee on 2016/5/6.
 */
public class WeatherFragment extends TabBaseFragment implements WeatherFragmentView, ViewPager.OnPageChangeListener {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.viewpager)
    MyViewPager viewpager;
    @Bind(R.id.right_icon)
    ImageView rightIcon;
    @Bind(R.id.back_icon)
    ImageView backIcon;
    private MyFragmentAdapter adapter;
    private List<TabBaseFragment> fragments;
    private Map<String, Object> fragmentMaps;
    private List<Map<String, Object>> fragmentList;
    private WeatherFragmentPresenterImpl weatherFragmentPresenter;

    public static WeatherFragment getInstance() {
        WeatherFragment weatherFragment = new WeatherFragment();
        return weatherFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_weather, null);
        ButterKnife.bind(this, view);
        weatherFragmentPresenter = new WeatherFragmentPresenterImpl(this);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(this);
        viewpager.setOffscreenPageLimit(5);
        title.setVisibility(View.VISIBLE);
        rightIcon.setVisibility(View.VISIBLE);
        rightIcon.setImageResource(R.mipmap.icon_plus);
        backIcon.setImageResource(R.mipmap.icon_menu);
        backIcon.setVisibility(View.VISIBLE);
        weatherFragmentPresenter.setCityData();
    }

    private void initData() {
        fragments = new ArrayList<TabBaseFragment>();
        fragmentMaps = new HashMap<String, Object>();
        fragmentList = new ArrayList<Map<String, Object>>();
        adapter = new MyFragmentAdapter(getChildFragmentManager(), fragmentList);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_weather, menu);
    }

    /**
     * 获取本地城市列表
     *
     * @param citys
     */
    @Override
    public void setCitys(List<LocalCityInfoMO> citys) {
        for (int i = 0; i < citys.size(); i++) {
//            fragments.add(ItemWeatherFragment.getInstance(citys.get(i)));
            add(citys.get(i));
        }
        adapter.setData(fragmentList);
    }

    public void add(LocalCityInfoMO cityInfoMO) {
        Map<String, Object> fragmentMap = new HashMap<>();
        fragmentMap.put("name", cityInfoMO.c3);
        fragmentMap.put("fragment", ItemWeatherFragment.getInstance(cityInfoMO));
        fragmentList.add(fragmentMap);
    }

    @Override
    public void setTitle(String city) {
        title.setText(city);
    }

    @Override
    public void showLocationDialog() {
//        showLoading("正在定位", true);
    }

    @Override
    public void locationFinish(String msg) {
        dismissLoading();
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG).setAction("编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    @Override
    public void addCity(LocalCityInfoMO localCityInfoMO) {
//        fragments.add(ItemWeatherFragment.getInstance(localCityInfoMO));
        add(localCityInfoMO);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        weatherFragmentPresenter.setTitle(position);
    }

    @Override
    public void onPageSelected(int position) {
        weatherFragmentPresenter.setTitle(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.right_icon, R.id.back_icon})
    public void onClick(View v) {
        if (v.getId() == R.id.right_icon) {
//            startActivityForResult(new Intent(getActivity(), AddCityActivity.class), 1001);
            startActivityForResult(new Intent(getActivity(), TestActivity.class), 1001);
        } else if (v.getId() == R.id.back_icon) {
            startActivityForResult(new Intent(getActivity(), EditCityActivity.class), 1002);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            LocalCityInfoMO localCityInfoMO = (LocalCityInfoMO) data.getSerializableExtra("LocalCityInfoMO");
            localCityInfoMO.addTime = Long.parseLong(TimeUtil.getSystem());
            LeHandler.getInstance().toastShow(getActivity(), localCityInfoMO.c3 + "添加成功");
            weatherFragmentPresenter.addCity(localCityInfoMO);
        } else if (resultCode == 1002) {
            Bundle bundle = data.getExtras();
//            List<LocalCityInfoMO> localCityInfoMOs = (List) bundle.get("citys");
//            LeHandler.getInstance().toastShow(getActivity(), "删除了" + localCityInfoMOs.size() + "个城市");
//            deleteCity(localCityInfoMOs);
        }
    }

    public void deleteCity(List<LocalCityInfoMO> cityInfoMOs) {
        Iterator<Map<String, Object>> iterator = fragmentList.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            for (LocalCityInfoMO localCityInfoMO : cityInfoMOs) {
                if (map.get("name").equals(localCityInfoMO.c3))
                    iterator.remove();
            }
        }
        weatherFragmentPresenter.refreshCity();
        adapter.notifyDataSetChanged();
    }
}
