package com.zhou.myweather.ui.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.base.adapter.BaseRecycleViewHoldler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zhou0618 on 2016/4/27 0027.
 */
public class HotCityAdapter extends BaseRecycleViewAdapter<HotCityAdapter.ViewHolder> {

    private List<String> cityNames;
    private Context mContext;

    public HotCityAdapter() {
    }

    public HotCityAdapter(Context mContext) {
        this.mContext = mContext;
        cityNames = new ArrayList<>();
    }

    public void setData(List<String> cityNames) {
        if (this.cityNames != null)
            this.cityNames.clear();
        this.cityNames.addAll(cityNames);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citys, null);
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
    public void onBindViewHolder(ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
        String cityName = cityNames.get(position);
        holder.itemView.setTag(cityNames);
        holder.cityName.setText(cityName);
    }

    class ViewHolder extends BaseRecycleViewHoldler {
        @Bind(R.id.cityName)
        TextView cityName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
