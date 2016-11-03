package com.zhou.myweather.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhou.myweather.R;
import com.zhou.myweather.base.TabBaseFragment;

/**
 * Created by Powerbee on 2016/5/4.
 */
public class TabFragment extends TabBaseFragment {

    public static TabFragment getInstance(){
        TabFragment tabFragment = new TabFragment();
        return tabFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tab_fragment,null);
        return view;
    }
}

