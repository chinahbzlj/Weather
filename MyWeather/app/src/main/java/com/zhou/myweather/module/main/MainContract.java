package com.zhou.myweather.module.main;

import com.zhou.myweather.base.BasePersenter;
import com.zhou.myweather.base.BaseView;

import java.util.List;

/**
 * Created by 周利杰 on 2017/7/24.
 */

public interface MainContract {
    interface View extends BaseView<Persenter> {
        void addCity();
        void notifyAdapter();
        void setTitle(String title);
        void setCitys(List<String> citys);
        void setCurrentItem(int item);

    }

    interface Persenter extends BasePersenter {
        void addCity(String cityName);
        void removeCity(int position);
        void setPosition(int position);
    }
}
