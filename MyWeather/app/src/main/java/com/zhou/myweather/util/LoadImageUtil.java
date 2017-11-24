package com.zhou.myweather.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class LoadImageUtil {
    public static void loadImage(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).load(url).into(imageView);
    }
}
