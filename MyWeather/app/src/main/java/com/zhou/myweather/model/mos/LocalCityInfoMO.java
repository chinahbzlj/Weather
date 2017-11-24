package com.zhou.myweather.model.mos;

import com.zhou.myweather.sdk.model.dto.CityInfoDTO;

/**
 * Created by Powerbee on 2016/5/18.
 */

public class LocalCityInfoMO extends BaseMO implements Comparable<LocalCityInfoMO> {

    /**
     * 区域id
     */
    public String c1;
    /** 城市级别 */
    public String c10;
    /** 城市区号 */
    public String c11;
    /** 邮编 */
    public String c12;
    /** 海拔 */
    public String c15;
    /** 雷达站号 */
    public String c16;
    /** */
    public String c17;
    /** 城市英文名 */
    public String c2;
    /** 城市中文名 */
    public String c3;
    /** 城市所在市英文名 */
    public String c4;
    /** 城市所在市中文名 */
    public String c5;
    /** 城市所在省英文名 */
    public String c6;
    /** 城市所在省中文名 */
    public String c7;
    /** 城市所在国家英文名 */
    public String c8;
    /** 城市所在国家中文名 */
    public String c9;
    /** 纬度 */
    public String latitude;
    /** 经度 */
    public String longitude;
    public Long addTime;

    public LocalCityInfoMO() {
    }

    public LocalCityInfoMO(CityInfoDTO cityInfoDTO) {
        super();
        this.c1 = cityInfoDTO.c1;
        this.c10 = cityInfoDTO.c10;
        this.c11 = cityInfoDTO.c11;
        this.c12 = cityInfoDTO.c12;
        this.c15 = cityInfoDTO.c15;
        this.c16 = cityInfoDTO.c16;
        this.c17 = cityInfoDTO.c17;
        this.c2 = cityInfoDTO.c2;
        this.c3 = cityInfoDTO.c3;
        this.c7 = cityInfoDTO.c7;
        this.c8 = cityInfoDTO.c8;
        this.c9 = cityInfoDTO.c9;
        this.latitude = cityInfoDTO.latitude;
        this.longitude = cityInfoDTO.longitude;
    }

    public LocalCityInfoMO(CityInfoDTO cityInfoDTO, long time) {
        super();
        this.c1 = cityInfoDTO.c1;
        this.c10 = cityInfoDTO.c10;
        this.c11 = cityInfoDTO.c11;
        this.c12 = cityInfoDTO.c12;
        this.c15 = cityInfoDTO.c15;
        this.c16 = cityInfoDTO.c16;
        this.c17 = cityInfoDTO.c17;
        this.c2 = cityInfoDTO.c2;
        this.c3 = cityInfoDTO.c3;
        this.c7 = cityInfoDTO.c7;
        this.c8 = cityInfoDTO.c8;
        this.c9 = cityInfoDTO.c9;
        this.latitude = cityInfoDTO.latitude;
        this.longitude = cityInfoDTO.longitude;
        this.addTime = time;
    }

    @Override
    public int compareTo(LocalCityInfoMO another) {
        return this.addTime.compareTo(another.addTime);
    }
}
