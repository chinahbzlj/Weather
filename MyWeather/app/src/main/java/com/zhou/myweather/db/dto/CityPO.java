package com.zhou.myweather.db.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.zhou.myweather.model.mos.BaseMO;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Powerbee on 2016/5/23.
 */
@Entity
public class CityPO extends BaseMO {

    public String areaid;
    public String nameed;
    public String namecn;
    public String districten;
    public String districtcn;
    private String proven;
    public String provcn;
    public String nationen;
    public String nationcn;
    @Generated(hash = 6147384)
    public CityPO(String areaid, String nameed, String namecn, String districten,
            String districtcn, String proven, String provcn, String nationen,
            String nationcn) {
        this.areaid = areaid;
        this.nameed = nameed;
        this.namecn = namecn;
        this.districten = districten;
        this.districtcn = districtcn;
        this.proven = proven;
        this.provcn = provcn;
        this.nationen = nationen;
        this.nationcn = nationcn;
    }
    @Generated(hash = 1699614865)
    public CityPO() {
    }
    public String getAreaid() {
        return this.areaid;
    }
    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
    public String getNameed() {
        return this.nameed;
    }
    public void setNameed(String nameed) {
        this.nameed = nameed;
    }
    public String getNamecn() {
        return this.namecn;
    }
    public void setNamecn(String namecn) {
        this.namecn = namecn;
    }
    public String getDistricten() {
        return this.districten;
    }
    public void setDistricten(String districten) {
        this.districten = districten;
    }
    public String getDistrictcn() {
        return this.districtcn;
    }
    public void setDistrictcn(String districtcn) {
        this.districtcn = districtcn;
    }
    public String getProven() {
        return this.proven;
    }
    public void setProven(String proven) {
        this.proven = proven;
    }
    public String getProvcn() {
        return this.provcn;
    }
    public void setProvcn(String provcn) {
        this.provcn = provcn;
    }
    public String getNationen() {
        return this.nationen;
    }
    public void setNationen(String nationen) {
        this.nationen = nationen;
    }
    public String getNationcn() {
        return this.nationcn;
    }
    public void setNationcn(String nationcn) {
        this.nationcn = nationcn;
    }

}
