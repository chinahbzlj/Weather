package com.zhou.myweather.module.weather.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.base.adapter.BaseRecycleViewHoldler;
import com.zhou.myweather.model.WeatherInfoManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zhou0618 on 2016/4/27 0027.
 */
public class HotCityAdapter extends BaseRecycleViewAdapter<HotCityAdapter.ViewHolder> {
    //
    private List<String> cityNames;
    private Context mContext;

    public HotCityAdapter() {
    }

    public HotCityAdapter(Context mContext) {
        this.mContext = mContext;
        cityNames = new ArrayList<String>();
    }

    public void setData(List<String> cityNames) {
        if (this.cityNames != null)
            this.cityNames.clear();
        this.cityNames.addAll(cityNames);
        this.notifyDataSetChanged();

    }

    //
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_citys, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (cityNames == null) {
            return 0;
        }
        return cityNames.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String cityName = cityNames.get(position);
        holder.itemView.setTag(cityNames);
        holder.cityName.setText(cityName);
        if (WeatherInfoManager.getWeatherInfoManager().isContains(cityName))
            holder.cityName.setTextColor(ContextCompat.getColor(mContext, R.color.gray153));
    }

    //
    class ViewHolder extends BaseRecycleViewHoldler {
        @Bind(R.id.cityName)
        TextView cityName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
