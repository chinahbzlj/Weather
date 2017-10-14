package com.zhou.myweather.module.weather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
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


    private void initView() {
//        hotCityAdapter = new HotCityAdapter(mContext);
//        hotCityAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View parent, int position) {
//
//            }
//        });
//        hotCityAdapter.setData(HotCityModel.getHotCitys());
        hotCityRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        hotCityRecyclerView.setAdapter(hotCityAdapter);

        adapter = new CitysAdapter();
//        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(final View parent, int position) {
//                final CityMO cityMO = (CityMO) parent.getTag();
//                new AlertDialog.Builder(mContext).setTitle("提示").setMessage("是否确定添加" + cityMO.namecn + "？").
//                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // TODO Auto-generated method stub
//                                QueryAreaIdForAreaRequest request = new QueryAreaIdForAreaRequest(cityMO.namecn);
//                                PBGlobal.getPbGlobal().getHttpEngine().senRequest(request, QueryAreaIdForAreaResponse.class, AddCityActivity.this);
//                                showLoading(R.string.loading);
//                                getHandler().sendEmptyMessageDelayed(200, 5 * 1000);
//                            }
//                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                    }
//                }).show();
//            }
//        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
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
}
