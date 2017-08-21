package com.zhou.myweather.net;

import com.zhou.myweather.sdk.model.dto.CityInfoDTO;
import com.zhou.myweather.sdk.model.dto.ForecastDTO;
import com.zhou.myweather.sdk.model.dto.NowStateDTO;

/**
 * Created by 周利杰 on 2017/8/19.
 */

public class WeatherDTO extends BaseDTO {

    public CityWeatherDTO showapi_res_body;

    public class CityWeatherDTO {
        public String time;
        public int ret_code;
        public boolean showapi_treemap;
        public NowStateDTO now;
        public CityInfoDTO cityInfo;
        public ForecastDTO f1;
        public ForecastDTO f2;
        public ForecastDTO f3;
        public ForecastDTO f4;
        public ForecastDTO f5;
        public ForecastDTO f6;
        public ForecastDTO f7;
    }
}
