package com.zhou.myweather.module.city;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.base.adapter.BaseRecycleViewAdapter;
import com.zhou.myweather.model.WeatherInfoManager;
import com.zhou.myweather.module.main.MainActivity;
import com.zhou.myweather.db.WeatherVO;
import com.zhou.myweather.util.LogcatUtil;
import com.zhou.myweather.widget.SwipeItemLayout;

import java.util.List;

/**
 * Created by 周利杰 on 2017/9/8.
 */

public class ManageCityActivity extends BaseActivity implements ManageCityContract.View {
    private static final String TAG = "ManageCityActivity";
    private ManageCityContract.Persenter persenter;
    private RecyclerView recyclerView;

    private List<WeatherVO> weatherPOJOS;
    private ManageAdapter manageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_city);
        new ManageCityPersenter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public void onAnimationFinished(RecyclerView.ViewHolder viewHolder) {
                super.onAnimationFinished(viewHolder);
                if (WeatherInfoManager.getWeatherInfoManager().getWeatherMap().size() == 0) {
                    startActivityForResult(new Intent(ManageCityActivity.this, AddCityActivity.class), MainActivity.ADD_CITY);
                    weatherPOJOS.clear();
                }
            }
        });
        toolbar.setTitle("管理城市");
        findViewById(R.id.add_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ManageCityActivity.this, AddCityActivity.class), MainActivity.ADD_CITY);
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ManageCityActivity.this,));
            }
        });
        manageAdapter = new ManageAdapter();
        manageAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View parent, int position) {
                LogcatUtil.d("点击"+position);
                Intent intent = new Intent();
                intent.putExtra("item", String.valueOf(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        recyclerView.setAdapter(manageAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        weatherPOJOS = WeatherInfoManager.getWeatherInfoManager().getWeatherMap();
        manageAdapter.setData(weatherPOJOS);
    }

    @Override
    public void setPersenter(ManageCityContract.Persenter persenter) {
        this.persenter = persenter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogcatUtil.d(resultCode);
        if (resultCode == RESULT_OK && requestCode == MainActivity.ADD_CITY) {
            String cityName = data.getStringExtra(AddCityActivity.CITY_NAME);
            if (!TextUtils.isEmpty(cityName)) {
                Intent intent = new Intent();
                intent.putExtra(AddCityActivity.CITY_NAME, cityName);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
