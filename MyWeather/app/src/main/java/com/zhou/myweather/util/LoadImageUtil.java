package com.zhou.myweather.util;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class LoadImageUtil {
    public static void loadImage(Context mContext, String url, ImageView imageView) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mContext).build();
        if (!ImageLoader.getInstance().isInited())
            ImageLoader.getInstance().init(configuration);
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
//				.
//				showStubImage(R.drawable.ic_launcher)
//				.showImageOnFail(R.drawable.ic_launcher)
                .imageScaleType(ImageScaleType.EXACTLY)
//				.showImageForEmptyUri(R.drawable.ic_launcher)
                .cacheInMemory(true)//.cacheOnDisc(true)
                .displayer(new FadeInBitmapDisplayer(300)).imageScaleType(ImageScaleType.EXACTLY).build();

        ImageLoader.getInstance().displayImage(url, imageView, displayImageOptions);
    }
}
