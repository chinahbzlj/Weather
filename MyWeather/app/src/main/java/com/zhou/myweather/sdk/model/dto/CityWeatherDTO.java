package com.zhou.myweather.sdk.model.dto;


import com.zhou.myweather.sdk.defines.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Powerbee on 2016/4/27.
 */
public class CityWeatherDTO {
    public CityInfoDTO cityInfoDTO;
    public List<ForecastDTO> forecastDTOList;
    public List<AqiDetailDTO> aqiDetailDTOs;
    public List<NowStateDTO> nowStateDTOs;
    public NowStateDTO nowStateDTO;
    public String time;
    public int retCode;
    public CityWeatherHolder cityWeatherHolder;
    public WeatherDTO weatherDTO;

    public CityWeatherDTO(Object data) {
        try {
            cityInfoDTO = new CityInfoDTO(((JSONObject) data).getJSONObject("cityInfo"));
            forecastDTOList = new ArrayList<ForecastDTO>();
            forecastDTOList.add(new ForecastDTO(((JSONObject) data).getJSONObject("f1"),"f1"));
            forecastDTOList.add(new ForecastDTO(((JSONObject) data).getJSONObject("f2"),"f2"));
            forecastDTOList.add(new ForecastDTO(((JSONObject) data).getJSONObject("f3"),"f3"));
            if (Config.needMoreDay.equals(Config.ON)) {
                forecastDTOList.add(new ForecastDTO(((JSONObject) data).getJSONObject("f4"),"f4"));
                forecastDTOList.add(new ForecastDTO(((JSONObject) data).getJSONObject("f5"),"f5"));
                forecastDTOList.add(new ForecastDTO(((JSONObject) data).getJSONObject("f6"),"f6"));
                forecastDTOList.add(new ForecastDTO(((JSONObject) data).getJSONObject("f7"),"f7"));
            }
            if (Config.needIndex.equals(Config.ON)) {
//                aqiDetailDTOs = (List<AqiDetailDTO>) ((JSONObject) data).getJSONArray("hourDataList");
            }
            if (Config.needHourData.equals(Config.ON)) {
                nowStateDTOs = new ArrayList<NowStateDTO>();
                JSONArray jsonArray = ((JSONObject) data).getJSONArray("hourDataList");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    nowStateDTOs.add(new NowStateDTO(jsonObject));
                }
            }
            nowStateDTO = new NowStateDTO(((JSONObject) data).getJSONObject("now"));
            retCode = ((JSONObject) data).getInt("ret_code");
            time = ((JSONObject) data).getString("time");

            weatherDTO = new WeatherDTO();
            weatherDTO.nowStateDTO = nowStateDTO;
            weatherDTO.forecastDTO = forecastDTOList.get(0);
            ForecastDTO forecastDTO = forecastDTOList.get(0);
//            cityWeatherHolder = new CityWeatherHolder(forecastDTOList, nowStateDTO.temperature, nowStateDTO.weather, forecastDTO.day_wind_direction, forecastDTO.day_wind_power,
//                    forecastDTO.day_air_temperature, forecastDTO.night_weather, forecastDTO.night_air_temperature, forecastDTO.weekday, forecastDTO.sun_begin_end, nowStateDTO.sd,
//                    nowStateDTO.wind_power, nowStateDTO.aqiDetailDTO.aqi, nowStateDTO.aqiDetailDTO.primary_pollutant, nowStateDTO.aqiDetailDTO.quality, nowStateDTO.aqiDetailDTO.pm2_5,
//                    nowStateDTO.wind_direction, time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
