package com.zhou.myweather.module.city;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.base.BaseView;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.base.adapter.BaseRecycleViewHoldler;
import com.zhou.myweather.core.MyApplication;
import com.zhou.myweather.db.WeatherDAO;
import com.zhou.myweather.db.dto.CityPO;
import com.zhou.myweather.model.HotCityModel;
import com.zhou.myweather.model.WeatherInfoManager;
import com.zhou.myweather.module.weather.EditChangeListener;
import com.zhou.myweather.module.weather.MyItemDecoration;
import com.zhou.myweather.module.weather.adapter.CitysAdapter;
import com.zhou.myweather.module.weather.adapter.HotCityAdapter;
import com.zhou.myweather.service.LocationService;
import com.zhou.myweather.util.ActivityUtils;
import com.zhou.myweather.util.LogcatUtil;
import com.zhou.myweather.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Powerbee on 2016/5/20.
 */
public class AddCityActivity extends BaseActivity implements AddCityContract.View {
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
    private HotCityAdapter hotCityAdapter;
    private List<CityPO> cityPOS;
    private SearchCityAdapter adapter;
    private AddCityContract.Persenter persenter;

    @Override
    public void handleMsg(Message msg) {
        super.handleMsg(msg);
        if (msg.what == 200) dismissLoading();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        new AddCityPersenter(this);
        toolbar.setTitle("添加城市");
        initView();
        persenter.start();
    }

    public static final String CITY_NAME = "cityName";
    public static final String SELECT_CITY_NAME = "selectcityName";

    private void initView() {
        hotCityAdapter = new HotCityAdapter(mContext);
        hotCityAdapter.setData(HotCityModel.getHotCitys());
        hotCityRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        hotCityRecyclerView.addItemDecoration(new MyItemDecoration(this, 3));
        hotCityRecyclerView.setAdapter(hotCityAdapter);
        hotCityAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View parent, int position) {
                String city = HotCityModel.getHotCitys().get(position);
                if (city.equals("当前位置")) {
                    ToastUtil.getInstance().toastShowS("正在搜索...");
                    getPersimmions();
                    persenter.startLocation();
                } else addCity(CITY_NAME, city);

            }
        });
        search.addTextChangedListener(new EditChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (TextUtils.isEmpty(s.toString())) {
                    hotCityRecyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    return;
                }
                persenter.queryAddCity(s.toString().trim());
            }
        });
        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hotCityRecyclerView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    hotCityRecyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });

        adapter = new SearchCityAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View parent, int position) {
                persenter.queryCity(position);
            }
        });
    }

    @Override
    public void setCitys(List<CityPO> citys) {
        this.cityPOS = citys;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void locationSuccess(String country, String city, String district) {
        persenter.stopLocation();
        addCity(CITY_NAME, district);
    }

    @Override
    public void selectCity(String namecn) {
        persenter.stopLocation();
        addCity(SELECT_CITY_NAME, namecn);
    }

    @Override
    public void addOrSelectCity(String type, String city) {
        if (WeatherInfoManager.getWeatherInfoManager().isContains(city)) {
            ToastUtil.getInstance().toastShowS(city + "-已存在");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(CITY_NAME, city);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    private void addCity(String type, String city) {
        if (WeatherInfoManager.getWeatherInfoManager().isContains(city)) {
            ToastUtil.getInstance().toastShowS("当前位置为：" + city + "-已存在");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(CITY_NAME, city);
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.icon_cancel)
    public void onClick() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        search.setText("");
        search.clearFocus();
    }

    @Override
    public void onBackPressed() {
        mfinish();
    }

    public void mfinish() {
        if (WeatherInfoManager.getWeatherInfoManager().getWeatherMap().size() != 0) finish();
        else ActivityUtils.getActivityUtils().exit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) mfinish();
        return true;
    }

    @Override
    public void setPersenter(AddCityContract.Persenter persenter) {
        this.persenter = persenter;
    }

    public class SearchCityAdapter extends BaseRecycleViewAdapter<SearchCityAdapter.ViewHolder> {

        @Override
        public SearchCityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citys_left, null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            super.onBindViewHolder(viewHolder, position);
            CityPO cityPO = cityPOS.get(position);
            String msg = cityPO.namecn + "，" + cityPO.nationcn + "，" + cityPO.provcn;
            viewHolder.city.setText(msg);

            if (WeatherInfoManager.getWeatherInfoManager().isContains(cityPO.namecn))
                viewHolder.city.setTextColor(ContextCompat.getColor(mContext, R.color.gray153));
        }

        @Override
        public int getItemCount() {
            return cityPOS.size();
        }

        public class ViewHolder extends BaseRecycleViewHoldler {
            private TextView city;

            public ViewHolder(View itemView) {
                super(itemView);
                city = (TextView) itemView.findViewById(R.id.cityName);
            }
        }
    }


    private String permissionInfo;

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    private final int SDK_PERMISSION_REQUEST = 127;

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

}
