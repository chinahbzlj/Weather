package com.zhou.myweather.module.weather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.model.HotCityModel;
import com.zhou.myweather.model.WeatherInfoManager;
import com.zhou.myweather.model.dao.CityDao;
import com.zhou.myweather.model.dao.CityInfoDao;
import com.zhou.myweather.model.mos.CityMO;
import com.zhou.myweather.model.mos.LocalCityInfoMO;
import com.zhou.myweather.module.weather.adapter.CitysAdapter;
import com.zhou.myweather.module.weather.adapter.HotCityAdapter;
import com.zhou.myweather.sdk.core.HttpEngine;
import com.zhou.myweather.sdk.defines.protocol.IServiceResponse;
import com.zhou.myweather.sdk.model.dto.AttributionDTO;
import com.zhou.myweather.sdk.model.response.QueryAreaIdForAreaResponse;
import com.zhou.myweather.util.LogUtil;
import com.zhou.myweather.util.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Powerbee on 2016/5/20.
 */
public class AddCityActivity extends BaseActivity implements HttpEngine.HttpRequestListener {
    private Context mContext = AddCityActivity.this;
    @Bind(R.id.icon_cancel)
    ImageView iconCancel;
    @Bind(R.id.search)
    EditText search;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.gridView)
    RecyclerView hotCityRecyclerView;
    @Bind(R.id.hotCityGridView)
    GridView hotCityGridView;
    private CitysAdapter adapter;
    private HotCityAdapter hotCityAdapter;

    @Override
    public void handleMsg(Message msg) {
        super.handleMsg(msg);
        if (msg.what == 200) {
            dismissLoading();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        toolbar.setTitle("添加城市");
        initView();

    }

    public static final String CITY_NAME = "cityName";

    private void initView() {
//        hotCityGridView.setAdapter(new HotCityAdapter());
        hotCityAdapter = new HotCityAdapter(mContext);
        hotCityAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View parent, int position) {

            }
        });
        hotCityAdapter.setData(HotCityModel.getHotCitys());
        hotCityRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        hotCityRecyclerView.addItemDecoration(new HotCityItemDecoration(this));
        hotCityRecyclerView.addItemDecoration(new MyItemDecoration(this, 3));
        hotCityRecyclerView.setAdapter(hotCityAdapter);
        hotCityAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View parent, int position) {
                String city = HotCityModel.getHotCitys().get(position);
                if (city.equals("当前城市")) {
                    ToastUtil.getInstance().toastShowS("暂时没有定位功能，请选择其他城市");
                } else {
                    if (WeatherInfoManager.getWeatherInfoManager().isContains(city)) return;
                    Intent intent = new Intent();
                    intent.putExtra(CITY_NAME, city);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        search.addTextChangedListener(new EditChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                //搜索城市
//                List<CityMO> cityMOs = CityDao.queryCityForName(s.toString().trim());
                List<CityMO> cityMOs = CityDao.queryCity(s.toString().trim());
                LogUtil.e("查询到的城市数量", cityMOs.size() + "");
//                adapter.setData(cityMOs);
            }
        });
    }

    @OnClick(R.id.icon_cancel)
    public void onClick() {

    }

    @Override
    public void responseResult(IServiceResponse response) {
        if (response instanceof QueryAreaIdForAreaResponse) {
            List<AttributionDTO> list = ((QueryAreaIdForAreaResponse) response).attributionDTOs;
            if (list.size() != 0) {
                LocalCityInfoMO cityInfoMO = new LocalCityInfoMO(list.get(0).cityInfoDTO);
                CityInfoDao.addOrUpdate(cityInfoMO);
                Intent intent = new Intent();
                intent.putExtra("LocalCityInfoMO", cityInfoMO);
                setResult(1001, intent);
                dismissLoading();
                finish();

            }
        }
    }

//    class HotCityAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return HotCityModel.getHotCitys().size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return HotCityModel.getHotCitys().get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            String city = HotCityModel.getHotCitys().get(position);
//            ViewHolder viewHolder;
//            if (convertView == null) {
//                viewHolder = new ViewHolder();
//                convertView = LayoutInflater.from(AddCityActivity.this).inflate(R.layout.item_citys, null);
//                viewHolder.city = (TextView) convertView.findViewById(R.id.cityName);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            viewHolder.city.setText(city);
//            return convertView;
//        }
//
//        class ViewHolder {
//            TextView city;
//        }
//    }
}
