package com.zhou.myweather.module.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.zhou.myweather.core.PBGlobal;
import com.zhou.myweather.model.dao.CityWeatherDao;
import com.zhou.myweather.model.dao.ForecastDao;
import com.zhou.myweather.model.mos.CityWeatherMO;
import com.zhou.myweather.model.mos.ForecastMO;
import com.zhou.myweather.model.mos.LocalCityInfoMO;
import com.zhou.myweather.module.weather.ItemWeatherFragment;
import com.zhou.myweather.module.weather.view.ItemWeatherFragmentView;
import com.zhou.myweather.util.DateUtil;
import com.zhou.myweather.sdk.core.HttpEngine;
import com.zhou.myweather.sdk.defines.protocol.IServiceResponse;
import com.zhou.myweather.sdk.model.request.QueryWeatherForCoordinateRequest;
import com.zhou.myweather.sdk.model.response.QueryWeatherResponse;

import java.util.List;

/**
 * Created by Powerbee on 2016/5/10.
 */
public class ItemWeatherFragmentPresenterImpl implements ItemWeatherFragmentPresenter, HttpEngine.HttpRequestListener {
    private ItemWeatherFragmentView itemWeatherFragmentView;
    private Context mContext;
    //保存从数据库中获取的天气信息
    private CityWeatherMO cityWeatherMO;

    public ItemWeatherFragmentPresenterImpl(ItemWeatherFragmentView itemWeatherFragment) {
        this.itemWeatherFragmentView = itemWeatherFragment;
        this.mContext = ((ItemWeatherFragment) itemWeatherFragment).getActivity();
    }


    @Override
    public void queryCityWeather(LocalCityInfoMO cityInfoMO) {
        QueryWeatherForCoordinateRequest request = new QueryWeatherForCoordinateRequest(cityInfoMO.latitude, cityInfoMO.longitude);
//        PBGlobal.getPbGlobal().getVolley().senRequest(request, QueryWeatherResponse.class, this);
//        PBGlobal.getPbGlobal().getHttpEngine().senRequest(request, QueryWeatherResponse.class, this);
        itemWeatherFragmentView.startRefresh();
    }

    @Override
    public void responseResult(IServiceResponse response) {
        if (response instanceof QueryWeatherResponse) {
            if (!response.isError()) {
                QueryWeatherResponse resp = (QueryWeatherResponse) response;
                Log.e("resp", resp.toString());
                CityWeatherMO cityWeatherMO = new CityWeatherMO(resp.cityWeatherDTO.cityWeatherHolder, itemWeatherFragmentView.getLocalCity().c3);
//                if (this.cityWeatherMO == null || (this.cityWeatherMO != null && !this.cityWeatherMO.time.equals(cityWeatherMO.time))) {
                CityWeatherDao.addCityWeather(cityWeatherMO);
                PBGlobal.getPbGlobal().getDataLocalWeather().addCityWeather(cityWeatherMO);
                ForecastDao.addOrUpdateCity(cityWeatherMO.forecastMOs);
                PBGlobal.getPbGlobal().getDataFoecast().refreshForecast(cityWeatherMO.forecastMOs);
                itemWeatherFragmentView.refreshView(cityWeatherMO);
                itemWeatherFragmentView.stopRefresh();
//                } else {
//                    itemWeatherFragmentView.stopRefresh();
//                }
            }
        }
    }

    /*
     返回是否未空
      */
    public boolean loadWeather() {
        List<ForecastMO> list = PBGlobal.getPbGlobal().getDataFoecast().getAllForecastForName(itemWeatherFragmentView.getLocalCity().c3);

        cityWeatherMO = PBGlobal.getPbGlobal().getDataLocalWeather().queryCityWeather(itemWeatherFragmentView.getLocalCity().c3);

        if (cityWeatherMO != null && list != null && list.size() != 0) {
            cityWeatherMO.forecastMOs.clear();
            cityWeatherMO.forecastMOs.addAll(list);
            itemWeatherFragmentView.refreshView(cityWeatherMO);
//            if(cityWeatherMO.time)
            if (list.size() != 0 && cityWeatherMO != null)
                return false;
        }
        return true;
    }

    public boolean isRefresh() {
        if (cityWeatherMO != null)
            return DateUtil.isRefresh(cityWeatherMO.time);
        return false;
    }
}
