package com.zhou.myweather.module.main.weather;

import android.content.Context;

import com.zhou.myweather.base.BaseSubscriber;
import com.zhou.myweather.core.MyApplication;
import com.zhou.myweather.db.WeatherDAO;
import com.zhou.myweather.db.WeatherVO;
import com.zhou.myweather.db.dto.ForecastPO;
import com.zhou.myweather.db.dto.WeatherPO;
import com.zhou.myweather.model.WeatherInfoManager;
import com.zhou.myweather.module.main.weather.WeatherContract.Persenter;
import com.zhou.myweather.net.CityAllWeatherInfoDTO;
import com.zhou.myweather.util.JSONHelper;
import com.zhou.myweather.util.LogcatUtil;
import com.zhou.myweather.util.TimeUtil;
import com.zhou.myweather.util.ToastUtil;
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
    private WeatherVO weatherPOJO;
    private Context mContext;

    public WeatherPersenter(WeatherContract.View view, String city) {
        this.view = view;
        this.view.setPersenter(this);
        compositeSubscription = new CompositeSubscription();
        this.city = city;
        this.mContext = ((CityWeatherFragment) view).getContext();
    }

    @Override
    public void start() {
    }

    public void get() {
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
                                LogcatUtil.d("请求错误：" + e.getMessage());
                            }

                            @Override
                            public void onNext(CityAllWeatherInfoDTO weatherDTO) {
                                LogcatUtil.d(JSONHelper.toJson(weatherDTO));
                                if (weatherDTO.showapi_res_code == 0) {
                                    weatherPOJO = new WeatherVO(city, weatherDTO);
                                    WeatherInfoManager.getWeatherInfoManager().addCityWeather(weatherPOJO);
                                    WeatherDAO.getWeatherDAO().insertWeather(weatherPOJO.toWeatherPO());
                                    WeatherDAO.getWeatherDAO().insertForecastPO(weatherPOJO.city_name, weatherPOJO.getForecastPOs());
                                    view.showWeather(weatherPOJO);
                                } else {
                                    ToastUtil.getInstance().toastShowS(weatherDTO.showapi_res_error);
                                    LogcatUtil.d("请求失败：" + weatherDTO.showapi_res_error);
                                }
                            }
                        })
        );
    }

    public void show(WeatherVO weatherVO) {
        view.showWeather(weatherVO);
        if (!TimeUtil.needRefresh(TimeUtil.getSystem(), weatherVO.localTime)) {
            return;
        }
        LogcatUtil.d("信息过期，需要重新获取");
        get();
    }

    @Override
    public void getWeather() {
        WeatherVO weatherPOJOTmp = WeatherInfoManager.getWeatherInfoManager().getWeatherPOJO(city);
        if (weatherPOJOTmp == null) {
            WeatherPO weatherPO = WeatherDAO.getWeatherDAO().getWeatherPO(city);
            if (weatherPO != null) {
                weatherPOJOTmp = new WeatherVO(weatherPO, WeatherDAO.getWeatherDAO().getForecastOPS(city));
                show(weatherPOJOTmp);
            } else get();
        } else show(weatherPOJOTmp);
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
