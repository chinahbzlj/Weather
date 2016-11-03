package com.zhou.myweather.ui.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.base.adapter.BaseRecycleViewHoldler;
import com.zhou.myweather.model.mos.LocalCityInfoMO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zhou0618 on 2016/4/27 0027.
 */
public class EditCitysAdapter extends BaseRecycleViewAdapter<EditCitysAdapter.ViewHolder> implements CompoundButton.OnCheckedChangeListener {

    //    private List<LocalCityInfoMO> cityMOs;
    private List<CityData> cityDatas;
    private Context mContext;

    public EditCitysAdapter() {

    }

    public EditCitysAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<LocalCityInfoMO> cityMOs) {
//        this.cityMOs = cityMOs;
        this.cityDatas = new ArrayList<>();
        for (LocalCityInfoMO cityInfoMO : cityMOs) {
            CityData cityData = new CityData();
            cityData.cityInfoMO = cityInfoMO;
            cityData.isSelect = false;
            this.cityDatas.add(cityData);
        }
        this.notifyDataSetChanged();
    }

    public List<LocalCityInfoMO> getSelectCity() {
        List<LocalCityInfoMO> cityInfoMOs = new ArrayList<>();
        for (CityData cityData : this.cityDatas) {
            if (cityData.isSelect)
                cityInfoMOs.add(cityData.cityInfoMO);
        }
        return cityInfoMOs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_city, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (cityDatas == null) {
            return 0;
        }
        return cityDatas.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
//        LocalCityInfoMO cityMO = cityMOs.get(position);
        CityData cityData = cityDatas.get(position);
        holder.itemView.setTag(cityData.cityInfoMO);
        holder.cityName.setText(cityData.cityInfoMO.c3);
        holder.orderNumber.setText(String.valueOf(position + 1));
        holder.select.setOnCheckedChangeListener(this);
        holder.select.setTag(cityData);
    }

    class CityData {
        private LocalCityInfoMO cityInfoMO;
        private boolean isSelect;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CityData cityData = (CityData) buttonView.getTag();
        cityData.isSelect = true;
    }


    class ViewHolder extends BaseRecycleViewHoldler {
        @Bind(R.id.order_number)
        TextView orderNumber;
        @Bind(R.id.city_name)
        TextView cityName;
        @Bind(R.id.select)
        CheckBox select;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
