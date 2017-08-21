package com.zhou.myweather.module.main.weather;

import com.zhou.myweather.module.main.weather.WeatherContract.Persenter;
import com.zhou.myweather.net.WeatherDTO;
import com.zhou.myweather.util.JSONHelper;
import com.zhou.myweather.util.LogcatUtil;
import com.zhou.myweather.util.TimeUtil;
import com.zhou.myweather.util.http.RetrofitHelper;

import retrofit2.adapter.rxjava.Result;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 周利杰 on 2017/7/26.
 */

public class WeatherPersenter implements Persenter {
    private WeatherContract.View view;
    private CompositeSubscription compositeSubscription;
    private String city;

    public WeatherPersenter(WeatherContract.View view, String city) {
        this.view = view;
        this.view.setPersenter(this);
        compositeSubscription = new CompositeSubscription();
        this.city = city;
    }

    @Override
    public void start() {
//        compositeSubscription.add(
        RetrofitHelper.getInstance().getDefaultRxApi()
                .getWeatherForArea("8f7d2a968c174372b5e14156a4ceb6a2","825", TimeUtil.getSystem(), "", city, "0", "0", "0", "0", "0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherDTO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherDTO result) {
                        LogcatUtil.d(JSONHelper.toJson(result));
                    }
                });
//                );
    }

    @Override
    public void detach() {

    }
}