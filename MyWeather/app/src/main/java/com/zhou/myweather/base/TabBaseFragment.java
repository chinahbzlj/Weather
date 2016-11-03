package com.zhou.myweather.base;

import android.app.ProgressDialog;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Handler;



/**
 * Created by Powerbee on 2016/5/4.
 */
public class TabBaseFragment extends Fragment {


    private ProgressDialog locationDialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TabBaseFragment.this.handleMessage(msg);
        }
    };

    public void handleMessage(Message msg) {

    }

    public void showLoading(String msg,boolean cancelable){
        if (locationDialog == null) {
            locationDialog = new ProgressDialog(getActivity());
            locationDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        }
        locationDialog.setCancelable(cancelable);// 设置是否可以通过点击Back键取消
        locationDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        locationDialog.setMessage(msg);
        locationDialog.show();
    }

    public void dismissLoading() {
        if (locationDialog != null && locationDialog.isShowing()) {
            locationDialog.dismiss();
            locationDialog.cancel();
        }
    }
    public Handler getHandler() {
        return handler;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if(ButterKnife.)
    }
}
