package com.zhou.myweather.module.city;

import com.zhou.myweather.base.BasePersenter;
import com.zhou.myweather.base.BaseView;
import com.zhou.myweather.db.dto.CityPO;

import java.util.List;

/**
 * Created by 周利杰 on 17-11-28.
 */

public class AddCityContract {
    interface View extends BaseView<Persenter> {
        void setCitys(List<CityPO> citys);
        void locationSuccess(String country,String city,String district);
        void selectCity(String namecn);
        void addOrSelectCity(String type,String namecn);
        void notifyAdapter();
    }

    interface Persenter extends BasePersenter {
        void startLocation();
        void stopLocation();
        /** 输入框中查询城市 **/
        void queryCity(int position);
        /** 从数据库中查询城市 **/
        void queryAddCity(String s);
    }
}
