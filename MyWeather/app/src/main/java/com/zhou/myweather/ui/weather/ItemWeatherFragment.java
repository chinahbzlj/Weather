package com.zhou.myweather.ui.weather;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.TabBaseFragment;
import com.zhou.myweather.model.mos.CityWeatherMO;
import com.zhou.myweather.model.mos.ForecastMO;
import com.zhou.myweather.model.mos.LocalCityInfoMO;
import com.zhou.myweather.ui.weather.adapter.ForecastsAdapter;
import com.zhou.myweather.ui.weather.presenter.ItemWeatherFragmentPresenterImpl;
import com.zhou.myweather.ui.weather.view.ItemWeatherFragmentView;
import com.zhou.myweather.util.LeHandler;
import com.zhou.myweather.util.LoadImageUtil;
import com.zhou.myweather.util.StringUtils;
import com.zhou.sdk.model.response.QueryWeatherResponse;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Powerbee on 2016/5/6.
 */
public class ItemWeatherFragment extends TabBaseFragment implements ItemWeatherFragmentView {

    @Bind(R.id.city_weather)
    TextView cityWeather;
    @Bind(R.id.city_weather_temperature)
    TextView cityWeatherTemperature;
    @Bind(R.id.week)
    TextView week;
    @Bind(R.id.highestTemperatures)
    TextView highestTemperatures;
    @Bind(R.id.lowestTemperature)
    TextView lowestTemperature;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.forecastsRecyclerView)
    RecyclerView forecastsRecyclerView;
    @Bind(R.id.forecasts)
    LinearLayout forecasts;
    @Bind(R.id.city_weather_info)
    TextView cityWeatherInfo;
    @Bind(R.id.city_sunup)
    TextView citySunup;
    @Bind(R.id.city_sunset)
    TextView citySunset;
    @Bind(R.id.temperature)
    TextView temperature;
    @Bind(R.id.humidity)
    TextView humidity;
    @Bind(R.id.wind_power)
    TextView windPower;
    @Bind(R.id.wind_direction)
    TextView windDirection;
    @Bind(R.id.aqi)
    TextView aqi;
    @Bind(R.id.primary_pollutant)
    TextView primaryPollutant;
    @Bind(R.id.quality)
    TextView quality;
    @Bind(R.id.pm2_5)
    TextView pm25;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    private String city;
    private ItemWeatherFragmentPresenterImpl itemWeatherFragmentPresenterImpl;
//    private List<ForecastDTO> list;
//    private ForecastDTO forecastDTO;
//    private CityInfoDTO cityInfoDTO;
//    private List<AqiDetailDTO> aqiDetailDTOs;
//    private List<NowStateDTO> nowStateDTOs;
//    private NowStateDTO nowStateDTO;
//    private String time;

    private ForecastsAdapter adapter;

    private LocalCityInfoMO cityInfoMO;

    public static TabBaseFragment getInstance(LocalCityInfoMO cityInfoMO) {
        ItemWeatherFragment itemWeatherFragment = new ItemWeatherFragment();
        itemWeatherFragment.cityInfoMO = cityInfoMO;
        return itemWeatherFragment;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_item_weather, null);
        ButterKnife.bind(this, v);
        adapter = new ForecastsAdapter(getActivity());
        itemWeatherFragmentPresenterImpl = new ItemWeatherFragmentPresenterImpl(this);
        initView();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        //如果数据中有改城市信息，则直接从数据库中加载，否则查询天气
        boolean flag1 = itemWeatherFragmentPresenterImpl.loadWeather();
        boolean flag2 = itemWeatherFragmentPresenterImpl.isRefresh();
        if (flag1 || flag2)
            LeHandler.getInstance().postDelayed(new Runnable() {
                @Override
                public void run() {
                    itemWeatherFragmentPresenterImpl.queryCityWeather(cityInfoMO);
                }
            }, 500);
    }

    private void initView() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemWeatherFragmentPresenterImpl.queryCityWeather(cityInfoMO);
            }
        });
        appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                refresh.setEnabled(verticalOffset != 0 ? false : true);
            }
        });
        cityWeatherTemperature.setText(city);
        forecastsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastsRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn, R.id.city_weather_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                break;
            case R.id.city_weather_info:
                break;
        }
    }


    @Override
    public void refreshView(Object o) {
        if (o instanceof QueryWeatherResponse) {
//            QueryWeatherResponse resp = (QueryWeatherResponse) o;
//            list = resp.cityWeatherDTO.forecastDTOList;
//            forecastDTO = list.get(0);
//            cityInfoDTO = resp.cityWeatherDTO.cityInfoDTO;
//            nowStateDTO = resp.cityWeatherDTO.nowStateDTO;
//            if (PBGlobal.getPbGlobal().needIndex.equals(Config.ON)) {
//                aqiDetailDTOs = resp.cityWeatherDTO.aqiDetailDTOs;
//            }
//            if (PBGlobal.getPbGlobal().needHourData.equals(Config.ON)) {
//                nowStateDTOs = resp.cityWeatherDTO.nowStateDTOs;
//            }
//            time = resp.cityWeatherDTO.time;
//
//
//            addView();
//            cityWeatherTemperature.setText(nowStateDTO.temperature + "°");
//            cityWeather.setText(nowStateDTO.weather);
//            cityWeatherInfo.setText("今天" + nowStateDTO.weather + "," + forecastDTO.day_wind_direction + " " + forecastDTO.day_wind_power +
//                    "。最高气温" + forecastDTO.day_air_temperature + "。今晚" + forecastDTO.night_weather + ",最低气温" + forecastDTO.night_air_temperature);
//            week.setText(StringUtils.getWeek(forecastDTO.weekday));
//            highestTemperatures.setText(forecastDTO.day_air_temperature);
//            lowestTemperature.setText(forecastDTO.night_air_temperature);
//            citySunup.setText(StringUtils.getSunBegin(forecastDTO.sun_begin_end));
//            citySunset.setText(StringUtils.getSunEnd(forecastDTO.sun_begin_end));
//            temperature.setText(nowStateDTO.temperature + "°");
//            humidity.setText(nowStateDTO.sd);
//            windPower.setText(nowStateDTO.wind_power);
//            aqi.setText(nowStateDTO.aqiDetailDTO.aqi);
//            primaryPollutant.setText(nowStateDTO.aqiDetailDTO.primary_pollutant);
//            quality.setText(nowStateDTO.aqiDetailDTO.quality);
//            pm25.setText(nowStateDTO.aqiDetailDTO.pm2_5);
//            windDirection.setText(nowStateDTO.wind_direction);
        } else if (o instanceof CityWeatherMO) {
            CityWeatherMO cityWeatherMO = (CityWeatherMO) o;
            addView(cityWeatherMO.forecastMOs);
            cityWeatherTemperature.setText(cityWeatherMO.temperature + "°");
            cityWeather.setText(cityWeatherMO.weather);
            cityWeatherInfo.setText("今天" + cityWeatherMO.weather + "," + cityWeatherMO.day_wind_direction + " " + cityWeatherMO.day_wind_power +
                    "。最高气温" + cityWeatherMO.day_air_temperature + "。今晚" + cityWeatherMO.night_weather + ",最低气温" + cityWeatherMO.night_air_temperature);
            week.setText(StringUtils.getWeek(cityWeatherMO.weekday));
            highestTemperatures.setText(cityWeatherMO.day_air_temperature);
            lowestTemperature.setText(cityWeatherMO.night_air_temperature);
            citySunup.setText(StringUtils.getSunBegin(cityWeatherMO.sun_begin_end));
            citySunset.setText(StringUtils.getSunEnd(cityWeatherMO.sun_begin_end));
            temperature.setText(cityWeatherMO.temperature + "°");
            humidity.setText(cityWeatherMO.sd);
            windPower.setText(cityWeatherMO.wind_power);
            aqi.setText(cityWeatherMO.aqi);
            primaryPollutant.setText(cityWeatherMO.primary_pollutant);
            quality.setText(cityWeatherMO.quality);
            pm25.setText(cityWeatherMO.pm2_5);
            windDirection.setText(cityWeatherMO.wind_direction);
        }


    }

    @Override
    public void stopRefresh() {
        refresh.setRefreshing(false);
    }

    @Override
    public void startRefresh() {
        refresh.setRefreshing(true);
    }

    @Override
    public LocalCityInfoMO getLocalCity() {
        return cityInfoMO;
    }

    private void addView(List<ForecastMO> list) {
        forecasts.removeAllViews();
        for (ForecastMO forecastMO : list) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_forecast, null);
            ((TextView) v.findViewById(R.id.item_forecast_week)).setText(StringUtils.getWeek(forecastMO.weekday));
            ((TextView) v.findViewById(R.id.forecast_highest_temperatures)).setText(forecastMO.day_air_temperature);
            ((TextView) v.findViewById(R.id.forecast_lowest_temperature)).setText(forecastMO.night_air_temperature);
            LoadImageUtil.loadImage(getActivity(), forecastMO.day_weather_pic, (ImageView) v.findViewById(R.id.item_forecast_icon));
            forecasts.addView(v);
        }
    }

    private void addView() {
//        forecasts.removeAllViews();
//        for (ForecastDTO forecastDTO : list) {
//            View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_forecast, null);
//            ((TextView) v.findViewById(R.id.item_forecast_week)).setText(StringUtils.getWeek(forecastDTO.weekday));
//            ((TextView) v.findViewById(R.id.forecast_highest_temperatures)).setText(forecastDTO.day_air_temperature);
//            ((TextView) v.findViewById(R.id.forecast_lowest_temperature)).setText(forecastDTO.night_air_temperature);
//            LoadImageUtil.loadImage(getActivity(), forecastDTO.day_weather_pic, (ImageView) v.findViewById(R.id.item_forecast_icon));
//            forecasts.addView(v);
//        }
    }


}