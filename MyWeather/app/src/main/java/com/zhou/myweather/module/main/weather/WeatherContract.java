package com.zhou.myweather.module.main.weather;

import com.zhou.myweather.base.BasePersenter;
import com.zhou.myweather.base.BaseView;
import com.zhou.myweather.db.WeatherVO;

/**
 * Created by 周利杰 on 2017/7/26.
 */

public interface WeatherContract {
    interface View extends BaseView<Persenter> {

        void showWeather(WeatherVO weatherPOJO);
    }

    interface Persenter extends BasePersenter {
        void getWeather();

        void interrupt();
    }
}
