package com.zhou.myweather.ui.weather;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.core.PBGlobal;
import com.zhou.myweather.model.dao.CityInfoDao;
import com.zhou.myweather.model.mos.LocalCityInfoMO;
import com.zhou.myweather.ui.weather.adapter.EditCitysAdapter;
import com.zhou.myweather.util.LeHandler;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 16-6-8.
 */
public class EditCityActivity extends BaseActivity {
    @Bind(R.id.right)
    TextView right;
    private Context mContext = EditCityActivity.this;
    @Bind(R.id.back_icon)
    ImageView backIcon;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private EditCitysAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcity);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        adapter = new EditCitysAdapter(mContext);
        adapter.setData(PBGlobal.getPbGlobal().getDataLocalCity().getAllLocalCity());
    }

    private void initView() {
        title.setText("编辑城市");
        title.setVisibility(View.VISIBLE);
        right.setText("删除");
        right.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.right_icon, R.id.right})
    public void onClick(View view) {
        if (view.getId() == R.id.right) {
            new AlertDialog.Builder(mContext).setTitle("提示").setMessage("是否删除").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    List<LocalCityInfoMO> cityInfoMOList = adapter.getSelectCity();
                    PBGlobal.getPbGlobal().getDataLocalCity().deleteLocalCitys(cityInfoMOList);
                    CityInfoDao.deleteCitys(cityInfoMOList);
                    LeHandler.getInstance().toastShow(mContext,"删除成功");
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("citys", (Serializable) cityInfoMOList);
                    intent.putExtras(bundle);
                    setResult(1002,intent);
                    finish();
                }
            }).setNegativeButton("取消", null).show();
        }
    }
}
