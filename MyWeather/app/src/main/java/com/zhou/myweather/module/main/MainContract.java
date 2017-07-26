package com.zhou.myweather.module.main;

import com.zhou.myweather.base.BasePersenter;
import com.zhou.myweather.base.BaseView;

/**
 * Created by 周利杰 on 2017/7/24.
 */

public interface MainContract {
    interface View extends BaseView<Persenter> {

    }

    interface Persenter extends BasePersenter {

    }
}
