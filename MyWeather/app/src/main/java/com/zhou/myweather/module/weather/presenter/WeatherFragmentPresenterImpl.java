package com.zhou.myweather.module.weather.presenter;

import android.content.Context;
import android.content.Intent;

//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.Poi;
import com.zhou.myweather.core.MyApplication;
import com.zhou.myweather.core.PBGlobal;
import com.zhou.myweather.location.LocationService;
import com.zhou.myweather.model.dao.CityDao;
import com.zhou.myweather.model.dao.CityInfoDao;
import com.zhou.myweather.model.mos.CityMO;
import com.zhou.myweather.model.mos.LocalCityInfoMO;
import com.zhou.myweather.module.weather.AddCityActivity;
import com.zhou.myweather.module.weather.WeatherFragment;
import com.zhou.myweather.module.weather.view.WeatherFragmentView;
import com.zhou.myweather.util.ExcelUtil;
import com.zhou.myweather.util.LeHandler;
import com.zhou.myweather.util.StringUtils;
import com.zhou.myweather.sdk.core.HttpEngine;
import com.zhou.myweather.sdk.defines.protocol.IServiceResponse;
import com.zhou.myweather.sdk.model.dto.AttributionDTO;
import com.zhou.myweather.sdk.model.request.QueryAreaIdForAreaRequest;
import com.zhou.myweather.sdk.model.request.QueryWeatherForCoordinateRequest;
import com.zhou.myweather.sdk.model.response.QueryAreaIdForAreaResponse;
import com.zhou.myweather.util.LogUtil;
import com.zhou.myweather.util.TimeUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Zhou0618 on 2016/5/9 0009.
 */
public class WeatherFragmentPresenterImpl implements WeatherFragmentPresenter, HttpEngine.HttpRequestListener {
    private WeatherFragmentView weatherFragment;
    private Context mContext;
    private LocationService locationService;
    private List<LocalCityInfoMO> cityInfoMOs;

    public WeatherFragmentPresenterImpl(WeatherFragmentView fragmentView) {
        this.weatherFragment = fragmentView;
        mContext = ((WeatherFragment) this.weatherFragment).getActivity();

    }

    @Override
    public void setCityData() {
        //如果数据库中城市列表为空，则讲表格中的数据录入到数据库中
        if (PBGlobal.getPbGlobal().getDataLocalCity().getAllLocalCitys().size() == 0) {
            //将城市信息从表格中录入到数据库中。
            LeHandler.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<CityMO> cityMOs = ExcelUtil.readCityExcelFile(mContext.getAssets().open("weather_areaid.xls"));
                        CityDao.addOrUpdateCity(cityMOs);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        if (cityInfoMOs != null && cityInfoMOs.size() != 0)
            cityInfoMOs.clear();
        cityInfoMOs = PBGlobal.getPbGlobal().getDataLocalCity().getAllLocalCity();
        Collections.sort(cityInfoMOs);
        if (cityInfoMOs == null || cityInfoMOs.size() == 0) {
            dingwei();
            LeHandler.getInstance().postAtTime(new Runnable() {
                @Override
                public void run() {
//                    locationService.stop();
//                    mContext.startActivity(new Intent(mContext, AddCityActivity.class));
//                    LeHandler.getInstance().toastShow(mContext, "请输入你所在的城市");
                }
            }, 10 * 1000);
        } else {
            weatherFragment.setCitys(cityInfoMOs);
        }
    }

    @Override
    public void setTitle(int position) {
        if (cityInfoMOs != null && cityInfoMOs.size() != 0)
            weatherFragment.setTitle(cityInfoMOs.get(position).c3);
    }

    @Override
    public void queryCityWeather(String city) {
        QueryAreaIdForAreaRequest request = new QueryAreaIdForAreaRequest(city);
//        PBGlobal.getPbGlobal().getVolley().senRequest(request, QueryAreaIdForAreaResponse.class, this);
//        PBGlobal.getPbGlobal().getHttpEngine().senRequest(request, QueryAreaIdForAreaResponse.class, this);
    }

    @Override
    public void queryCityWeather(String lat, String lng) {
        QueryWeatherForCoordinateRequest request = new QueryWeatherForCoordinateRequest(lat, lng);
//        PBGlobal.getPbGlobal().getVolley().senRequest(request, QueryAreaIdForAreaResponse.class, this);
//        PBGlobal.getPbGlobal().getHttpEngine().se/nRequest(request, QueryAreaIdForAreaResponse.class, this);
    }

    @Override
    public void addCity(LocalCityInfoMO localCityInfoMO) {

        CityInfoDao.addOrUpdate(localCityInfoMO);
        PBGlobal.getPbGlobal().getDataLocalCity().addLocalCity(localCityInfoMO);
//        PBGlobal.getPbGlobal()
        cityInfoMOs.add(localCityInfoMO);
        weatherFragment.addCity(localCityInfoMO);
    }


    public void dingwei() {
        weatherFragment.showLocationDialog();
//        locationService = MyApplication.getInstance().locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
//        locationService.registerListener(mListener);
        //注册监听
        int type = 0;
        if (type == 1) {
//            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
//            locationService.setLocationOption(locationService.getOption());
        }
//        locationService.start();// 定位SDK
    }


    @Override
    public void responseResult(IServiceResponse response) {
        if (response instanceof QueryAreaIdForAreaResponse) {
            if (!response.isError()) {
                QueryAreaIdForAreaResponse resp = (QueryAreaIdForAreaResponse) response;
                List<AttributionDTO> list = resp.attributionDTOs;
                if (list.size() != 0) {
                    LocalCityInfoMO cityInfoMO = new LocalCityInfoMO(list.get(0).cityInfoDTO, Long.parseLong(TimeUtil.getSystem()));
                    CityInfoDao.addOrUpdate(cityInfoMO);
                    cityInfoMOs = CityInfoDao.queryAllCity();
                    if (cityInfoMOs != null && cityInfoMOs.size() != 0) {
                        weatherFragment.setCitys(cityInfoMOs);
                        weatherFragment.setTitle(cityInfoMOs.get(0).c3);
                    }
                }
            }
        }
    }


//    public BDLocationListener mListener = new BDLocationListener() {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // TODO Auto-generated method stub
//            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
////                roadGPSDTO = new RoadGPSDTO();
//                StringBuffer sb = new StringBuffer(256);
//                sb.append("time : ");
//                /**
//                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
//                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
//                 */
//
//                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                    sb.append("\nspeed : ");
//                    sb.append(location.getSpeed());// 单位：km/h
////                    roadGPSDTO.speed = location.getSpeed();
//                    sb.append("\nsatellite : ");
//                    sb.append(location.getSatelliteNumber());
////                    roadGPSDTO.satellite = location.getSatelliteNumber();
//                    sb.append("\nheight : ");
//                    sb.append(location.getAltitude());// 单位：米
////                    roadGPSDTO.height = location.getAltitude();
//                    sb.append("\ndescribe : ");
////                    roadGPSDTO.describe = "gps定位成功";0
//                    sb.append("gps定位成功");
//                    locationService.stop();
//                    weatherFragment.locationFinish("gps定位成功");
//                    queryCityWeather(StringUtils.getArea(location.getDistrict()));
////                    queryCityWeather(location.getLatitude(),location.getLongitude());
//                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                    // 运营商信息
//                    sb.append("\noperationers : ");
//                    sb.append(location.getOperators());
////                    roadGPSDTO.operationers = location.getOperators();
//                    sb.append("\ndescribe : ");
//                    sb.append("网络定位成功");
////                    roadGPSDTO.describe = "网络定位成功";
//                    locationService.stop();
//                    weatherFragment.locationFinish("gps定位成功");
//
//                    queryCityWeather(StringUtils.getArea(location.getDistrict()));
//                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                    sb.append("\ndescribe : ");
//                    sb.append("离线定位成功，离线定位结果也是有效的");
////                    roadGPSDTO.describe = "离线定位成功，离线定位结果也是有效的";
//                } else if (location.getLocType() == BDLocation.TypeServerError) {
//                    sb.append("\ndescribe : ");
//                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
////                    roadGPSDTO.describe = "服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因";
//                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
////                    roadGPSDTO.describe = "网络不同导致定位失败，请检查网络是否通畅";
//                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                    sb.append("\ndescribe : ");
//                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
////                    roadGPSDTO.describe = "无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机";
//                }
//                sb.append(location.getTime());
////                roadGPSDTO.time = location.getTime();
//                sb.append("\nerror code : ");
////                roadGPSDTO
//                sb.append(location.getLocType());
//                sb.append("\nlatitude : ");
//                sb.append(location.getLatitude());
////                roadGPSDTO.latitude = location.getLatitude();
//                sb.append("\nlontitude : ");
//                sb.append(location.getLongitude());
////                roadGPSDTO.lontitude = location.getLongitude();
//                sb.append("\nradius : ");
//                sb.append(location.getRadius());
////                roadGPSDTO.radius = location.getRadius();
//                sb.append("\nCountryCode : ");
//                sb.append(location.getCountryCode());
////                roadGPSDTO.CountryCode = location.getCountryCode();
//                sb.append("\nCountry : ");
//                sb.append(location.getCountry());
////                roadGPSDTO.Country = location.getCountry();
//                sb.append("\ncitycode : ");
//                sb.append(location.getCityCode());
////                roadGPSDTO.citycode = location.getCityCode();
//                sb.append("\ncity : ");
//                sb.append(location.getCity());
////                roadGPSDTO.city = location.getCity();
//                sb.append("\nDistrict : ");
//                sb.append(location.getDistrict());
////                roadGPSDTO.District = location.getDistrict();
//                sb.append("\nStreet : ");
//                sb.append(location.getStreet());
////                roadGPSDTO.Street = location.getStreet();
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
////                roadGPSDTO.addr = location.getAddrStr();
//                sb.append("\nDescribe: ");
//                sb.append(location.getLocationDescribe());
////                roadGPSDTO.Describe = location.getLocationDescribe();
//                sb.append("\nDirection(not all devices have value): ");
//                sb.append(location.getDirection());
////                roadGPSDTO.Direction = location.getDirection();
//                sb.append("\nPoi: ");
//                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
//                    for (int i = 0; i < location.getPoiList().size(); i++) {
//                        Poi poi = (Poi) location.getPoiList().get(i);
//                        sb.append(poi.getName() + ";");
////                        roadGPSDTO.Poi += poi.getName() + ";";
//                    }
//                }
//                logMsg(sb.toString());
//            }
//        }
//
//        private void logMsg(String s) {
//            LogUtil.e("百度地图定位结果", s);
//        }
//    };

    public void refreshCity() {
        cityInfoMOs = PBGlobal.getPbGlobal().getDataLocalCity().getAllLocalCity();
    }
}
