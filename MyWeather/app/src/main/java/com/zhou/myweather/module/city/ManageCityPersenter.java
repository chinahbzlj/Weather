package com.zhou.myweather.module.city;

/**
 * Created by 周利杰 on 2017/9/30.
 */

public class ManageCityPersenter implements ManageCityContract.Persenter {
    private ManageCityContract.View view;

    public ManageCityPersenter(ManageCityContract.View view) {
        this.view = view;

    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {

    }
}
