package com.zhou.myweather.model.mos;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Powerbee on 2016/5/23.
 */

@DatabaseTable(tableName = "CityTable")
public class CityMO extends BaseMO {

    @DatabaseField(id = true)
    public String areaid;
    @DatabaseField
    public String nameed;
    @DatabaseField
    public String namecn;
    @DatabaseField
    public String districten;
    @DatabaseField
    public String districtcn;
    @DatabaseField
    private String proven;
    @DatabaseField
    public String provcn;
    @DatabaseField
    public String nationen;
    @DatabaseField
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
