package com.zhou.myweather.module.main.presenter;


import com.zhou.myweather.module.main.view.MainView;
import com.zhou.myweather.model.MainModel;
import java.util.List;
import java.util.Map;

/**
 * Created by Powerbee on 2016/5/6.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
    }
    @Override
    public void setTabButton(boolean isSelect,int position) {
    }

    @Override
    public void setTabButton(int position) {
        mainView.setTab(position);
    }

    @Override
    public void setMainAdapter() {
        List<Map<String, Object>> fragmentList = MainModel.getMainAdapterData();
        mainView.setMainAdapter(fragmentList);
    }
}
