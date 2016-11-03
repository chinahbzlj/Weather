package com.zhou.myweather.ui.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.base.adapter.BaseRecycleViewHoldler;
import com.zhou.myweather.model.mos.CityMO;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zhou0618 on 2016/4/27 0027.
 */
public class CitysAdapter extends BaseRecycleViewAdapter<CitysAdapter.ViewHolder> {

    private List<CityMO> cityMOs;
    private Context mContext;

    public CitysAdapter() {

    }

    public CitysAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<CityMO> cityMOs) {
        this.cityMOs = cityMOs;
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
        if (cityMOs == null) {
            return 0;
        }
        return cityMOs.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position,payloads);
        CityMO cityMO = cityMOs.get(position);
        holder.itemView.setTag(cityMO);
        holder.cityName.setText(cityMO.namecn);
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
