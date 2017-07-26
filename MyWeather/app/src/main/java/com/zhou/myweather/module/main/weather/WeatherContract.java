package com.zhou.myweather.module.main.weather;

import com.zhou.myweather.base.BasePersenter;
import com.zhou.myweather.base.BaseView;

/**
 * Created by 周利杰 on 2017/7/26.
 */

public interface WeatherContract {
    interface View extends BaseView<Persenter> {

    }

    interface Persenter extends BasePersenter {

    }
}
