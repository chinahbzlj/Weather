package com.zhou.myweather.module.main.view;

import java.util.List;
import java.util.Map;

/**
 * Created by Powerbee on 2016/5/6.
 */
public interface MainView {
    void setTab(int position);
    void setMainAdapter(List<Map<String, Object>> fragmentList);
}
