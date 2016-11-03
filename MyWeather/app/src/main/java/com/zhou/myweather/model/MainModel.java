package com.zhou.myweather.model;

import com.zhou.myweather.R;
import com.zhou.myweather.core.PBGlobal;
import com.zhou.myweather.ui.calendar.CalendarFragment;
import com.zhou.myweather.ui.weather.WeatherFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhou0618 on 2016/5/9 0009.
 */
public class MainModel {


    public static List<Map<String, Object>> getMainAdapterData() {
        //        fragments = new ArrayList<>();
//        fragments.add(TabFragment.class);
//        fragments.add(TabFragment.class);
//        fragments.add(TabFragment.class);
//        fragmentTitle = new ArrayList<>();
//        fragmentTitle.add("天气");
//        fragmentTitle.add("日历");
//        fragmentTitle.add("我的");
//
//        fragmentIcon = new ArrayList<>();
//        fragmentIcon.add(R.mipmap.ic_1);
//        fragmentIcon.add(R.mipmap.ic_2);
//        fragmentIcon.add(R.mipmap.ic_2);

        List<Map<String, Object>> fragmentList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("fragment", WeatherFragment.getInstance());
        map.put("title", "天气");
        map.put("icon", R.mipmap.ic_1);
        fragmentList.add(map);
        if (!PBGlobal.getPbGlobal().isOne) {
            Map<String, Object> map2 = new HashMap<>();
            map2.put("fragment", CalendarFragment.getInstance());
            map2.put("title", "日历");
            map2.put("icon", R.mipmap.ic_2);
            fragmentList.add(map2);
        }
//
//        Map<String, Object> map3 = new HashMap<>();
//        map3.put("fragment", TabFragment.getInstance());
//        map3.put("title", "我");
//        map3.put("icon", R.mipmap.ic_2);
//        fragmentList.add(map3);
        return fragmentList;
    }
}
