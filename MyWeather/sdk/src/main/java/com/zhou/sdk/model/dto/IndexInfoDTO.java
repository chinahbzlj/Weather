package com.zhou.sdk.model.dto;


import com.zhou.sdk.model.dto.index.BeautyDTO;
import com.zhou.sdk.model.dto.index.ClothesDTO;
import com.zhou.sdk.model.dto.index.ColdDTO;
import com.zhou.sdk.model.dto.index.ComfortDTO;
import com.zhou.sdk.model.dto.index.GlassDTO;
import com.zhou.sdk.model.dto.index.SportsDTO;
import com.zhou.sdk.model.dto.index.TravelDTO;
import com.zhou.sdk.model.dto.index.UvDTO;
import com.zhou.sdk.model.dto.index.WashCarDTO;

import org.json.JSONObject;

/**
 * Created by zhou on 2015-09-09.
 * 指数对象
 */
public class IndexInfoDTO {
    /**  */
    public BeautyDTO beauty;
    /**  */
    public ClothesDTO clothes;
    /**  */
    public ColdDTO cold;
    /**  */
    public ComfortDTO comfort;
    /**  */
    public GlassDTO glass;
    /**  */
    public SportsDTO sports;
    /**  */
    public TravelDTO travel;
    /**  */
    public UvDTO uv;
    /**  */
    public WashCarDTO wash_car;

    public IndexInfoDTO(JSONObject data) {
//        this.beauty = new BeautyDTO(data.optJSONObject("beauty"));
        this.clothes = new ClothesDTO(data.optJSONObject("clothes"));
        this.cold = new ColdDTO(data.optJSONObject("cold"));
//        this.comfort = new ComfortDTO(data.optJSONObject("comfort"));
//        this.glass = new GlassDTO(data.optJSONObject("glass"));
//        this.sports =  new SportsDTO(data.optJSONObject("sports"));
//        this.travel = new TravelDTO(data.optJSONObject("travel"));
//        this.uv = new UvDTO(data.optJSONObject("uv"));
//        this.wash_car = new WashCarDTO(data.optJSONObject("wash_car"));
    }
}
