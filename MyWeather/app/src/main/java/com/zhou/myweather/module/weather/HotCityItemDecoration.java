package com.zhou.myweather.module.weather;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhou.myweather.R;

/**
 * Created by 周利杰 on 17-11-7.
 */

public class HotCityItemDecoration extends RecyclerView.ItemDecoration {
    private Paint dividerPaint;
    private int dividerHeight;
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;

    public HotCityItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        this.dividerPaint = new Paint();
        this.dividerPaint.setColor(ContextCompat.getColor(context, R.color.gray153));
        this.dividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.divider_height);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
        outRect.top = dividerHeight;
        outRect.left = dividerHeight;
        outRect.right = dividerHeight;

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
//        for (int i = 0; i < 1; i++) {
            View view = parent.getChildAt(i);
            float left = view.getLeft();
            float right = view.getRight() - dividerHeight;
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, dividerPaint);
            c.drawRect(left, 0, right, dividerHeight, dividerPaint);
            if (i < 3)
                c.drawRect(left, 0, right, dividerHeight, dividerPaint);
        }

    }
}
