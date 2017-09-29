package com.zhou.myweather.module.main.weather;

import com.zhou.myweather.base.BaseSubscriber;
import com.zhou.myweather.module.main.weather.WeatherContract.Persenter;
import com.zhou.myweather.net.CityAllWeatherInfoDTO;
import com.zhou.myweather.util.JSONHelper;
import com.zhou.myweather.util.LogcatUtil;
import com.zhou.myweather.util.TimeUtil;
import com.zhou.myweather.util.http.RetrofitHelper;

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
    private WeatherPOJO weatherPOJO;

    public WeatherPersenter(WeatherContract.View view, String city) {
        this.view = view;
        this.view.setPersenter(this);
        compositeSubscription = new CompositeSubscription();
        this.city = city;
    }

    @Override
    public void start() {
    }


    @Override
    public void getWeather() {
        if (compositeSubscription.isUnsubscribed())
            compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(
                RetrofitHelper.getInstance().getDefaultRxApi()
                        .getWeatherForArea("8f7d2a968c174372b5e14156a4ceb6a2", "825", TimeUtil.getSystem(), "", city, "1", "0", "1", "1", "1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseSubscriber<CityAllWeatherInfoDTO>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                LogcatUtil.d("请求失败：");
                            }

                            @Override
                            public void onNext(CityAllWeatherInfoDTO weatherDTO) {
                                LogcatUtil.d(JSONHelper.toJson(weatherDTO));
                                if (weatherDTO.showapi_res_code == 0) {
                                    weatherPOJO = new WeatherPOJO(weatherDTO);
                                    view.showWeather(weatherPOJO);
                                } else {
                                    LogcatUtil.d("请求失败：" + weatherDTO.showapi_res_error);
                                }
                            }
                        })
        );
    }

    @Override
    public void interrupt() {
        if (!compositeSubscription.isUnsubscribed()) compositeSubscription.unsubscribe();
    }

    @Override
    public void detach() {
        if (!compositeSubscription.isUnsubscribed()) compositeSubscription.unsubscribe();
    }
}
