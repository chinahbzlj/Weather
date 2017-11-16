package com.zhou.myweather.base;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.zhou.myweather.R;
import com.zhou.myweather.core.PBGlobal;
import com.zhou.myweather.util.ActivityUtils;
import com.zhou.myweather.util.LogUtil;
import com.zhou.myweather.util.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by Powerbee on 2016/5/4.
 */
public class BaseActivity extends AppCompatActivity {
    private static String TAG = BaseActivity.class.getName();
    public Toolbar toolbar;
    public ProgressDialog loading;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BaseActivity.this.handleMsg(msg);
        }
    };

    public void handleMsg(Message msg) {

    }

    public Handler getHandler() {
        return this.handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.getActivityUtils().pushActivity(this);

        PBGlobal.getPbGlobal().setAppContext(getApplicationContext());
        if (PBGlobal.getPbGlobal().getAppContext() != null) {
            PBGlobal.getPbGlobal().init(BaseActivity.this);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        setupToolbar();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.getActivityUtils().popActivity(this);
    }

    public TextView titleTextView;

    private void setupToolbar() {
        toolbar = ButterKnife.findById(this, R.id.tool_bar);
        titleTextView = ButterKnife.findById(this, R.id.title);
        if (toolbar == null) {
            LogUtil.i(TAG, "Didn't find a toolbar");
            return;
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    public void showLoading(int msgRes) {
        String string = getResources().getString(msgRes);
        showLoading(string, false);
    }

    public void showLoading(String msg, boolean cancelable) {
        if (loading == null) {
            loading = new ProgressDialog(this);
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        }
        loading.setCancelable(cancelable);// 设置是否可以通过点击Back键取消
        loading.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        loading.setMessage(msg);
        loading.show();
    }

    public void dismissLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
            loading.cancel();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
