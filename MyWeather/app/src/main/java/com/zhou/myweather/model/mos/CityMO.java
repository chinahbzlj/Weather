package com.zhou.myweather.model.mos;


/**
 * Created by Powerbee on 2016/5/23.
 */
public class CityMO extends BaseMO {

    public String areaid;
    public String nameed;
    public String namecn;
    public String districten;
    public String districtcn;
    private String proven;
    public String provcn;
    public String nationen;
    public String nationcn;

    public CityMO() {
    }

    public CityMO(String areaid, String nameed, String namecn, String districten, String districtcn, String proven, String provcn, String nationen, String nationcn) {
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
}
