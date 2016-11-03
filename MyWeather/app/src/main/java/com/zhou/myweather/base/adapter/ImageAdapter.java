package com.zhou.myweather.base.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {
    private Context context;
    private ViewPager viewPager;
    static final String arrarys[] = {"http://p1.meituan.net/movie/f1e42208897d8674bb7aab89fb078baf487236.jpg",
            "http://p1.meituan.net/movie/aa3c2bac8f9aaa557e63e20d56e214dc192471.jpg",
            "http://p0.meituan.net/movie/07b7f22e2ca1820f8b240f50ee6aa269481512.jpg"
            , "http://p1.meituan.net/movie/395266f5f470b027ed1ac87110b03b88149862.jpg"};
    ArrayList<ImageView> views;
    private int currentPage = 0;

    public ImageAdapter(Context context, ViewPager viewPager) {
        this.context = context;
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(this);
        views = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            views.add(imageView);
        }
        initImageData();
    }

    private void initImageData() {
        for (int i = 0; i < 3; i++) {
            ImageView imageView = views.get(i);
            if (i == 0) {
                Picasso.with(context).load(arrarys[arrarys.length - 1]).into(imageView);
            } else {
                Picasso.with(context).load(arrarys[i - 1]).into(imageView);
            }
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = views.get(position);
//Picasso.with(context).load(arrarys[position]).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(final int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            //在滚动完成之后
            case ViewPager.SCROLL_STATE_IDLE:
                if (viewPager.getCurrentItem() == 1) {
                    //如果位置没有变终止循环
                    break;
                }
                if (viewPager.getCurrentItem() > 1) {
                    currentPage++;
                } else {
                    currentPage--;
                }
                if (currentPage == arrarys.length) {
                    currentPage = 0;
                }
                if (currentPage == -1) {
                    currentPage = arrarys.length - 1;
                }
                if (currentPage == 0) {
                    Picasso.with(context).load(arrarys[arrarys.length - 1]).into(views.get(0));
                } else {
                    Picasso.with(context).load(arrarys[currentPage - 1]).into(views.get(0));
                }
                Picasso.with(context).load(arrarys[currentPage]).into(views.get(1));
                if (currentPage + 1 == arrarys.length) {
                    Picasso.with(context).load(arrarys[0]).into(views.get(2));
                } else {
                    Picasso.with(context).load(arrarys[currentPage + 1]).into(views.get(2));
                }
                viewPager.setCurrentItem(1, false);
                break;
        }
    }
}