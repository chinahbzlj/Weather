package com.zhou.myweather.ui.main.presenter;

/**
 * Created by Powerbee on 2016/5/6.
 */
public interface MainPresenter {
    void setTabButton(boolean isSelect, int position);
    void setTabButton(int position);
    void setMainAdapter();
}
