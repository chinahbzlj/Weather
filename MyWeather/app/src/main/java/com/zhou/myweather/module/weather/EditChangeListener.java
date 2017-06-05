package com.zhou.myweather.module.weather;

import android.text.Editable;
import android.text.TextWatcher;

import com.zhou.myweather.util.LogUtil;

/**
 * Created by Powerbee on 2016/5/20.
 */
public class EditChangeListener implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        LogUtil.sysout("beforeTextChanged=", s.toString() + " start=" + start + " count=" + count + " after=" + after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        LogUtil.sysout("onTextChanged=", s.toString() + " start=" + start + " befor=" + before + " count=" + count);
    }

    @Override
    public void afterTextChanged(Editable s) {

        LogUtil.sysout("afterTextChanged", s.toString());
    }
}
