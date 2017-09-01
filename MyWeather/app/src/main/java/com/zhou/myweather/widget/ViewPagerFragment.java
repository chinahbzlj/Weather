package com.zhou.myweather.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by 周利杰 on 2017/8/24.
 * <p>
 * ViewPager中使用Fragment时，onFragmentVisibleChange监控Fragment是在显示状态还是在隐藏状态
 */

public abstract class ViewPagerFragment extends Fragment {

    /**
     * rootView是否初始化，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态，防止因为ViewPager的缓存机制导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的View，修饰为protected，所以子类继承该类时，在onCreateView里必须进行初始化
     */
    protected View rootView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) return;

        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    protected void onFragmentVisibleChange(boolean isVisible) {
//        Log.w(getTAG(), "onFragmentVisibleChange -> isVisible: " + isVisible);
    }
}
