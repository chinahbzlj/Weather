package com.zhou.myweather.module.city;

import android.text.TextUtils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.zhou.myweather.core.MyApplication;
import com.zhou.myweather.db.WeatherDAO;
import com.zhou.myweather.db.dto.CityPO;
import com.zhou.myweather.service.LocationService;
import com.zhou.myweather.util.LogcatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周利杰 on 17-11-28.
 */

public class AddCityPersenter implements AddCityContract.Persenter {
    private AddCityContract.View view;
    private LocationService locationService;
    private List<CityPO> cityPOS = new ArrayList<>();

    public AddCityPersenter(AddCityContract.View view) {
        this.view = view;
        this.view.setPersenter(this);
    }

    @Override
    public void start() {
        locationService = MyApplication.getInstance().locationService;
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        view.setCitys(cityPOS);
    }

    @Override
    public void startLocation() {
        locationService.start();
    }

    @Override
    public void stopLocation() {
        locationService.stop();
    }

    @Override
    public void queryCity(int position) {
        String city = cityPOS.get(position).namecn;
        view.addOrSelectCity(AddCityActivity.CITY_NAME,city);
    }

    @Override
    public void queryAddCity(String s) {
        cityPOS.clear();
        cityPOS.addAll(WeatherDAO.getWeatherDAO().queryCity(s));
        view.notifyAdapter();
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                LogcatUtil.d(sb.toString());
                String country = location.getCountry();
                String city = location.getCity();
                String district = location.getDistrict();
                if (!TextUtils.isEmpty(country) && !country.equals("null")
                        && !TextUtils.isEmpty(city) && !city.equals("null")
                        && !TextUtils.isEmpty(district) && !district.equals("null")) {
                    String districtTail = district.substring(district.length() - 1);
                    String districtHead = district.substring(0, district.length() - 1);
                    //即使不包含区的地址，也需要判断是否已经添加过了

                    if (districtTail.equals("区") && cityIsContains(districtHead)) {
//                        LogcatUtil.d("最后一字是区");//定位得到的地址，如徐汇区和地址表格中的徐汇相差最后一个字
//                        //说明改地区已经添加了
                        view.selectCity(districtHead);
                    } else
                        view.locationSuccess(location.getCountry(), location.getCity(), location.getDistrict());
                }
            }
        }

    };

    private boolean cityIsContains(String city) {
        List<CityPO> cityPOs = WeatherDAO.getWeatherDAO().accurateQueryCity(city);
        if (cityPOS == null || cityPOS.size() == 0) return false;
        for (CityPO cityPO : cityPOs) {
            if (cityPO.equals(city)) return true;
        }
        return false;
    }

    @Override
    public void detach() {

    }
}
