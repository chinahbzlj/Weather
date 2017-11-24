package com.zhou.myweather.module.city;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.base.adapter.BaseRecycleViewHoldler;
import com.zhou.myweather.module.main.CityManagerListenerManager;
import com.zhou.myweather.db.WeatherVO;
import com.zhou.myweather.util.LogcatUtil;
import com.zhou.myweather.util.ToastUtil;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 周利杰 on 2017/9/30.
 */

public class ManageAdapter extends BaseRecycleViewAdapter<ManageAdapter.ViewHolder> {
    private List<WeatherVO> weatherPOJOS;
    private Context mContext;

    public ManageAdapter(List<WeatherVO> weatherPOJOS) {
        this.weatherPOJOS = weatherPOJOS;
    }

    public ManageAdapter() {

    }

    public void setData(List<WeatherVO> weatherPOJOS) {
        this.weatherPOJOS = weatherPOJOS;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        if (weatherPOJOS == null || weatherPOJOS.get(position) == null) {
            viewHolder.tvCityMsg.setVisibility(View.VISIBLE);
//            ToastUtil.getInstance().toastShowS("跳转");
            LogcatUtil.d("改城市的信息为空");
            return;
        }
        viewHolder.tvCityWeather.setText(weatherPOJOS.get(position).city_weather);
        viewHolder.tvCityTemperature.setText(weatherPOJOS.get(position).city_weather_temperature + "°");
        viewHolder.tvCityName.setText(weatherPOJOS.get(position).city_name);
    }

    @Override
    public int getItemCount() {
        return weatherPOJOS.size();
    }

    public class ViewHolder extends BaseRecycleViewHoldler implements View.OnClickListener {
        private TextView tvCityName, tvCityTemperature, tvCityWeather, tvCityMsg;
        private ImageButton delete;
        private RelativeLayout rlManagerCityItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCityName = (TextView) itemView.findViewById(R.id.tv_city_name);
            tvCityTemperature = (TextView) itemView.findViewById(R.id.tv_city_temperature);
            tvCityWeather = (TextView) itemView.findViewById(R.id.tv_city_weather);
            tvCityMsg = (TextView) itemView.findViewById(R.id.tv_city_msg);
            delete = (ImageButton) itemView.findViewById(R.id.delete);
            delete.setOnClickListener(this);
            rlManagerCityItem = (RelativeLayout) itemView.findViewById(R.id.rl_manager_city_item);
            rlManagerCityItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.delete) {
                int pos = getAdapterPosition();
                weatherPOJOS.remove(pos);
                CityManagerListenerManager.getCityManagerListenerManager().getCityManagerListener().removeCity(pos);
                notifyItemRemoved(pos);
            } else if (v.getId() == R.id.rl_manager_city_item) {
                Intent intent = new Intent();
                intent.putExtra("item", getAdapterPosition() + "");
                ((ManageCityActivity) mContext).setResult(RESULT_OK, intent);
                ((ManageCityActivity) mContext).finish();
            }
        }
    }
}

