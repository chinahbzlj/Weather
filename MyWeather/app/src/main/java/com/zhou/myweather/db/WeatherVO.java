package com.zhou.myweather.db;

import com.zhou.myweather.db.dto.ForecastPO;
import com.zhou.myweather.db.dto.WeatherPO;
import com.zhou.myweather.net.CityAllWeatherInfoDTO;
import com.zhou.myweather.sdk.model.dto.ForecastDTO;
import com.zhou.myweather.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周利杰 on 2017/9/3.
 * <p>
 * 展示数据，从服务器获取的数据太多，太杂，需要转换之后才能方便使用
 * WeatherVO(CityAllWeatherInfoDTO weatherDTO)，从json解析的数据转换过来
 * toWeatherPO()，需要将数据保存到数据库中，以便节省流量。所以将数据转换成数据库对象
 * setForecastPOS(List<ForecastPO> forecastDTOS)，将数据库对象转换成展示数据
 */

public class WeatherVO {
    public String city_name;
    public String city_weather;
    public String city_weather_temperature;
    public String week;
    public String highestTemperatures;
    public String lowestTemperature;
    public String city_weather_info;
    public String city_sunup;
    public String city_sunset;
    //    public String temperature;
    public String humidity;
    public String wind_power;
    public String wind_direction;
    public String aqi;
    public String primary_pollutant;
    public String quality;
    public String pm2_5;
    public String time;
    public String localTime;
    public List<ForecastDTO> forecastDTOS;


    public WeatherVO(String cityName, CityAllWeatherInfoDTO weatherDTO) {
        this.city_name = cityName;
        this.city_weather = weatherDTO.showapi_res_body.now.weather;
        this.city_weather_temperature = weatherDTO.showapi_res_body.now.temperature;
        this.week = getWeek(weatherDTO.showapi_res_body.f1.weekday);
        this.highestTemperatures = weatherDTO.showapi_res_body.f1.day_air_temperature;
        this.lowestTemperature = weatherDTO.showapi_res_body.f1.night_air_temperature;
        this.city_weather_info = "今天白天" + weatherDTO.showapi_res_body.now.weather + "," + weatherDTO.showapi_res_body.f1.day_wind_direction + " "
                + weatherDTO.showapi_res_body.f1.day_wind_power + "。最高气温" + weatherDTO.showapi_res_body.f1.day_air_temperature + "。今晚"
                + weatherDTO.showapi_res_body.f1.night_weather + ",最低气温" + weatherDTO.showapi_res_body.f1.night_air_temperature;
        this.city_sunup = weatherDTO.showapi_res_body.f1.sun_begin_end.substring(0, weatherDTO.showapi_res_body.f1.sun_begin_end.indexOf("|"));
        this.city_sunset = weatherDTO.showapi_res_body.f1.sun_begin_end.substring(weatherDTO.showapi_res_body.f1.sun_begin_end.indexOf("|") + 1);
//        this.temperature = weatherDTO.showapi_res_body.now.temperature;
        this.humidity = weatherDTO.showapi_res_body.now.sd;
        this.wind_power = weatherDTO.showapi_res_body.now.wind_power;
        this.wind_direction = weatherDTO.showapi_res_body.now.wind_direction;
        this.aqi = weatherDTO.showapi_res_body.now.aqi;
        this.primary_pollutant = weatherDTO.showapi_res_body.now.aqiDetail.primary_pollutant;
        this.quality = weatherDTO.showapi_res_body.now.aqiDetail.quality;
        this.pm2_5 = weatherDTO.showapi_res_body.now.aqiDetail.pm2_5;
        this.time = weatherDTO.showapi_res_body.time;
        this.localTime = TimeUtil.getSystem();
        this.forecastDTOS = new ArrayList<>();
        if (weatherDTO.showapi_res_body.f1 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f1);
        if (weatherDTO.showapi_res_body.f2 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f2);
        if (weatherDTO.showapi_res_body.f3 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f3);
        if (weatherDTO.showapi_res_body.f4 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f4);
        if (weatherDTO.showapi_res_body.f5 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f5);
        if (weatherDTO.showapi_res_body.f6 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f6);
        if (weatherDTO.showapi_res_body.f7 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f7);
    }

    public WeatherVO(CityAllWeatherInfoDTO weatherDTO) {
        this.city_name = weatherDTO.showapi_res_body.cityInfo.c3;
        this.city_weather = weatherDTO.showapi_res_body.now.weather;
        this.city_weather_temperature = weatherDTO.showapi_res_body.now.temperature;
        this.week = getWeek(weatherDTO.showapi_res_body.f1.weekday);
        this.highestTemperatures = weatherDTO.showapi_res_body.f1.day_air_temperature;
        this.lowestTemperature = weatherDTO.showapi_res_body.f1.night_air_temperature;
        this.city_weather_info = "今天白天" + weatherDTO.showapi_res_body.now.weather + "," + weatherDTO.showapi_res_body.f1.day_wind_direction + " "
                + weatherDTO.showapi_res_body.f1.day_wind_power + "。最高气温" + weatherDTO.showapi_res_body.f1.day_air_temperature + "。今晚"
                + weatherDTO.showapi_res_body.f1.night_weather + ",最低气温" + weatherDTO.showapi_res_body.f1.night_air_temperature;
        this.city_sunup = weatherDTO.showapi_res_body.f1.sun_begin_end.substring(0, weatherDTO.showapi_res_body.f1.sun_begin_end.indexOf("|"));
        this.city_sunset = weatherDTO.showapi_res_body.f1.sun_begin_end.substring(weatherDTO.showapi_res_body.f1.sun_begin_end.indexOf("|") + 1);
//        this.temperature = weatherDTO.showapi_res_body.now.temperature;
        this.humidity = weatherDTO.showapi_res_body.now.sd;
        this.wind_power = weatherDTO.showapi_res_body.now.wind_power;
        this.wind_direction = weatherDTO.showapi_res_body.now.wind_direction;
        this.aqi = weatherDTO.showapi_res_body.now.aqi;
        this.primary_pollutant = weatherDTO.showapi_res_body.now.aqiDetail.primary_pollutant;
        this.quality = weatherDTO.showapi_res_body.now.aqiDetail.quality;
        this.pm2_5 = weatherDTO.showapi_res_body.now.aqiDetail.pm2_5;
        this.time = weatherDTO.showapi_res_body.time;
        this.forecastDTOS = new ArrayList<>();
        if (weatherDTO.showapi_res_body.f1 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f1);
        if (weatherDTO.showapi_res_body.f2 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f2);
        if (weatherDTO.showapi_res_body.f3 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f3);
        if (weatherDTO.showapi_res_body.f4 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f4);
        if (weatherDTO.showapi_res_body.f5 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f5);
        if (weatherDTO.showapi_res_body.f6 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f6);
        if (weatherDTO.showapi_res_body.f7 != null)
            this.forecastDTOS.add(weatherDTO.showapi_res_body.f7);
    }

    public WeatherVO(WeatherPO po, List<ForecastPO> forecastPOS) {
        this.city_name = po.city_name;
        this.city_weather = po.city_weather;
        this.city_weather_temperature = po.city_weather_temperature;
        this.week = po.week;
        this.highestTemperatures = po.highestTemperatures;
        this.lowestTemperature = po.lowestTemperature;
        this.city_weather_info = po.city_weather_info;
        this.city_sunup = po.city_sunup;
        this.city_sunset = po.city_sunset;
//        this.temperature = weatherDTO.temperature;
        this.humidity = po.humidity;
        this.wind_power = po.wind_power;
        this.wind_direction = po.wind_direction;
        this.aqi = po.aqi;
        this.primary_pollutant = po.primary_pollutant;
        this.quality = po.quality;
        this.pm2_5 = po.pm2_5;
        this.time = po.time;
        this.localTime = po.localTime;
        this.forecastDTOS = new ArrayList<>();
        for (ForecastPO forecastPO : forecastPOS) {
            forecastDTOS.add(new ForecastDTO(forecastPO));
        }
    }


    public WeatherPO toWeatherPO() {
        WeatherPO weatherDTO = new WeatherPO();
        weatherDTO.city_name = this.city_name;
        weatherDTO.city_weather = this.city_weather;
        weatherDTO.city_weather_temperature = this.city_weather_temperature;
        weatherDTO.week = this.week;
        weatherDTO.highestTemperatures = this.highestTemperatures;
        weatherDTO.lowestTemperature = this.lowestTemperature;
        weatherDTO.city_weather_info = this.city_weather_info;
        weatherDTO.city_sunup = this.city_sunup;
        weatherDTO.city_sunset = this.city_sunset;
//        this.temperature = weatherDTO.temperature;
        weatherDTO.humidity = this.humidity;
        weatherDTO.wind_power = this.wind_power;
        weatherDTO.wind_direction = this.wind_direction;
        weatherDTO.aqi = this.aqi;
        weatherDTO.primary_pollutant = this.primary_pollutant;
        weatherDTO.quality = this.quality;
        weatherDTO.pm2_5 = this.pm2_5;
        weatherDTO.time = this.time;
        weatherDTO.localTime = this.localTime;
        return weatherDTO;
    }


    public List<ForecastPO> getForecastPOs() {
        List<ForecastPO> forecastDTOList = new ArrayList<>();
        for (ForecastDTO forecastDTO : forecastDTOS) {
            forecastDTO.name = this.city_name;
            forecastDTOList.add(forecastDTO.toForecastDTO());
        }
        return forecastDTOList;
    }

    public void setForecastPOS(List<ForecastPO> forecastDTOS) {
        List<ForecastDTO> forecastDTOS1 = new ArrayList<>();
        for (ForecastPO forecastDTO : forecastDTOS) {
            forecastDTOS1.add(new ForecastDTO(forecastDTO));
        }
        this.forecastDTOS = forecastDTOS1;
    }

    private String getWeek(String week) {
        if (week.equals("1")) {
            return "星期一";
        } else if (week.equals("2")) {
            return "星期二";
        } else if (week.equals("3")) {
            return "星期三";
        } else if (week.equals("4")) {
            return "星期四";
        } else if (week.equals("5")) {
            return "星期五";
        } else if (week.equals("6")) {
            return "星期六";
        } else if (week.equals("7")) {
            return "星期七";
        }
        return "";
    }

    public String getCity_name() {
        return this.city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_weather() {
        return this.city_weather;
    }

    public void setCity_weather(String city_weather) {
        this.city_weather = city_weather;
    }

    public String getCity_weather_temperature() {
        return this.city_weather_temperature;
    }

    public void setCity_weather_temperature(String city_weather_temperature) {
        this.city_weather_temperature = city_weather_temperature;
    }

    public String getWeek() {
        return this.week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getHighestTemperatures() {
        return this.highestTemperatures;
    }

    public void setHighestTemperatures(String highestTemperatures) {
        this.highestTemperatures = highestTemperatures;
    }

    public String getLowestTemperature() {
        return this.lowestTemperature;
    }

    public void setLowestTemperature(String lowestTemperature) {
        this.lowestTemperature = lowestTemperature;
    }

    public String getCity_weather_info() {
        return this.city_weather_info;
    }

    public void setCity_weather_info(String city_weather_info) {
        this.city_weather_info = city_weather_info;
    }

    public String getCity_sunup() {
        return this.city_sunup;
    }

    public void setCity_sunup(String city_sunup) {
        this.city_sunup = city_sunup;
    }

    public String getCity_sunset() {
        return this.city_sunset;
    }

    public void setCity_sunset(String city_sunset) {
        this.city_sunset = city_sunset;
    }

//    public String getTemperature() {
//        return this.temperature;
//    }
//
//    public void setTemperature(String temperature) {
//        this.temperature = temperature;
//    }

    public String getHumidity() {
        return this.humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind_power() {
        return this.wind_power;
    }

    public void setWind_power(String wind_power) {
        this.wind_power = wind_power;
    }

    public String getWind_direction() {
        return this.wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getAqi() {
        return this.aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPrimary_pollutant() {
        return this.primary_pollutant;
    }

    public void setPrimary_pollutant(String primary_pollutant) {
        this.primary_pollutant = primary_pollutant;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getPm2_5() {
        return this.pm2_5;
    }

    public void setPm2_5(String pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
