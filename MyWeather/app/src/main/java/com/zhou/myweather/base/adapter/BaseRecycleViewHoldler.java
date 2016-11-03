package com.zhou.myweather.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhou on 2016-04-27.
 */
public class BaseRecycleViewHoldler extends RecyclerView.ViewHolder{
    public BaseRecycleViewHoldler(View itemView) {
        super(itemView);
        itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
