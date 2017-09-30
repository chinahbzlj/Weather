package com.zhou.myweather.module.city;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.base.adapter.BaseRecycleViewHoldler;
import com.zhou.myweather.module.main.weather.WeatherPOJO;
import com.zhou.myweather.net.CityAllWeatherInfoDTO;

import java.util.List;

/**
 * Created by 周利杰 on 2017/9/30.
 */

public class ManageAdapter extends BaseRecycleViewAdapter<ManageAdapter.ViewHolder> {
    private List<WeatherPOJO> weatherPOJOS;

    public ManageAdapter(List<WeatherPOJO> weatherPOJOS) {
        this.weatherPOJOS = weatherPOJOS;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        if (weatherPOJOS == null || weatherPOJOS.get(position) == null) {
            viewHolder.tvCityMsg.setVisibility(View.VISIBLE);
            return;
        }
        viewHolder.tvCityWeather.setText(weatherPOJOS.get(position).city_weather);
        viewHolder.tvCityTemperature.setText(weatherPOJOS.get(position).city_weather_temperature);
        viewHolder.tvCityName.setText(weatherPOJOS.get(position).city_name);
    }

    @Override
    public int getItemCount() {
        return weatherPOJOS.size();
    }

    public static class ViewHolder extends BaseRecycleViewHoldler {
        private TextView tvCityName, tvCityTemperature, tvCityWeather, tvCityMsg;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.tv_city_name);
            tvCityTemperature = (TextView) itemView.findViewById(R.id.tv_city_temperature);
            tvCityWeather = (TextView) itemView.findViewById(R.id.tv_city_weather);
            tvCityMsg = (TextView) itemView.findViewById(R.id.tv_city_msg);
        }
    }
}

