package com.zhou.myweather.module.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.base.adapter.BaseRecycleViewHoldler;
import com.zhou.myweather.sdk.model.dto.ForecastDTO;
import com.zhou.myweather.util.LoadImageUtil;
import com.zhou.myweather.util.StringUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zhou0618 on 2016/4/27 0027.
 */
public class ForecastsAdapter extends BaseRecycleViewAdapter<ForecastsAdapter.ViewHolder> {

    private List<ForecastDTO> forecastDTOs;
    private Context mContext;

    public ForecastsAdapter(List<ForecastDTO> forecastDTOs) {
        this.forecastDTOs = forecastDTOs;
    }

    public ForecastsAdapter() {

    }


    public void setData(List<ForecastDTO> forecastDTOs) {
        this.forecastDTOs = forecastDTOs;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (forecastDTOs == null) {
            return 0;
        }
        return forecastDTOs.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ForecastDTO forecastDTO = forecastDTOs.get(position);
        holder.itemForecastWeek.setText(StringUtils.getWeek(forecastDTO.weekday));
        holder.forecastHighestTemperatures.setText(forecastDTO.day_air_temperature);
        holder.forecastLowestTemperature.setText(forecastDTO.night_air_temperature);
        LoadImageUtil.loadImage(mContext, forecastDTO.day_weather_pic, holder.itemForecastIcon);
    }

    class ViewHolder extends BaseRecycleViewHoldler {
        @Bind(R.id.item_forecast_week)
        TextView itemForecastWeek;
        @Bind(R.id.item_forecast_icon)
        ImageView itemForecastIcon;
        @Bind(R.id.forecast_highest_temperatures)
        TextView forecastHighestTemperatures;
        @Bind(R.id.forecast_lowest_temperature)
        TextView forecastLowestTemperature;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
