package com.zhou.myweather.module.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhou.myweather.R;
import com.zhou.myweather.base.BaseActivity;
import com.zhou.myweather.core.PBGlobal;
import com.zhou.myweather.module.PermissionsActivity;
import com.zhou.myweather.module.main.adapter.MyFragmentAdapter;
import com.zhou.myweather.module.main.presenter.MainPresenterImpl;
import com.zhou.myweather.module.main.view.MainView;
import com.zhou.myweather.util.PermissionsChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

public class MainActivity2 extends BaseActivity implements MainView, TabLayout.OnTabSelectedListener {
    private Context mContext = MainActivity2.this;

    private static final int REQUEST_CODE = 0; // 请求码
    @Bind(R.id.viewpager)
    MyViewPager viewpager;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    private List<Map<String, Object>> fragmentList;
    private MyFragmentAdapter myFragmentAdapter;
    private MainPresenterImpl mainPresenter;
    private static int REQUEST_STORAGE_PERMISSION = 1;
    private PermissionsChecker permissionsChecker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mainPresenter = new MainPresenterImpl(this);
        permissionsChecker = new PermissionsChecker(this);
        initData();
        initView();
    }

    private String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void onStart() {
        super.onStart();
//        Acp.getInstance(this).request(new AcpOptions.Builder().setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION).build(), new AcpListener() {
//            @Override
//            public void onGranted() {
//
//            }
//
//            @Override
//            public void onDenied(List<String> permissions) {
//
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (permissionsChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
//            case WRITE_EXTERNAL_STORAGE_REQUEST_CODE:
//                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    private void initData() {
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mainPresenter.setMainAdapter();
    }

    private List<Viewholder> viewholders;

    class Viewholder {
        TextView title;
        ImageView icon;
    }

    /**
     * 初始化adapter 并加载下方tab按钮
     */
    private void initView() {
        viewpager.setAdapter(myFragmentAdapter);
        viewpager.setNoScroll(true);
        tablayout.setupWithViewPager(viewpager);
        viewholders = new ArrayList<Viewholder>();
        if (PBGlobal.getPbGlobal().isOne) {
            tablayout.setVisibility(View.GONE);
        }
        tablayout.addOnTabSelectedListener(this);
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            if (tab != null) {
                Viewholder viewholder = new Viewholder();
                View v = LayoutInflater.from(mContext).inflate(R.layout.layout_tablayout_buttom, null);
                viewholder.title = (TextView) v.findViewById(R.id.title);
                viewholder.icon = (ImageView) v.findViewById(R.id.icon);
                viewholder.title.setText((String) fragmentList.get(i).get("title"));
                viewholder.title.setTextColor(Color.GRAY);
                viewholder.icon.setImageResource((int) fragmentList.get(i).get("icon"));
                viewholders.add(viewholder);
                if (i == 0)
                    mainPresenter.setTabButton(i);
                tab.setCustomView(v);
            }
        }
    }

    /**
     * 点击下方tab按钮时改变点击的按钮字体颜色
     *
     * @param tab
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mainPresenter.setTabButton(tab.getPosition());
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    /**
     * 刷新下方tab按钮状态
     *
     * @param position
     */
    @Override
    public void setTab(int position) {
        for (int i = 0; i < viewholders.size(); i++) {
            viewholders.get(i).title.setTextColor(i == position ? Color.WHITE : Color.GRAY);
        }
    }

    @Override
    public void setMainAdapter(List<Map<String, Object>> fragmentList) {
        this.fragmentList = fragmentList;
        myFragmentAdapter.setData(fragmentList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PBGlobal.getPbGlobal().exitSystem();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            exitApp();
        return false;
    }

    private long exitTime = 0;

    public void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            this.finish();
        }
    }

}