package com.zhou.myweather.module.calendar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhou.myweather.R;
import com.zhou.myweather.base.TabBaseFragment;
import com.zhou.myweather.util.DateUtil;

import butterknife.OnClick;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

/**
 * Created by Powerbee on 2016/5/11.
 */
public class CalendarFragment extends TabBaseFragment {


    //    @Bind(R.id.datePicker)
    DatePicker datePicker;

    public static CalendarFragment getInstance() {
        CalendarFragment calendarFragment = new CalendarFragment();
        return calendarFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_calendar, null);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        datePicker.setDate(DateUtil.getYear(), DateUtil.getMonth());
        datePicker.setMode(DPMode.SINGLE);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        DatePicker picker = new DatePicker(getActivity());
        picker.setDate(2015, 10);
        picker.setMode(DPMode.SINGLE);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }
}

