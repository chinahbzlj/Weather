package com.zhou.myweather.module.calendar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Created by Powerbee on 2016/5/11.
 */
public class CalendarWidght extends View implements View.OnTouchListener {
    private Context mContext;
    private Surface mSurface;
    private float mWidth, mHeight;
    private Calendar cCalendar, startCalendar;
    private Date today;

    public CalendarWidght(Context context) {
        this(context, null);
    }

    public CalendarWidght(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarWidght(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (attrs != null)
            for (int i = 0; i < attrs.getAttributeCount(); i++) {
                Log.e("attr", attrs.getAttributeName(i) + ":" + attrs.getAttributeValue(i));
            }
        today = new Date();
        mSurface = new Surface();
        setCalendar(Calendar.getInstance());
        this.setOnTouchListener(this);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed)
            mSurface.init();
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mSurface.box, mSurface.linePaint);
        if (clicked) {
            mSurface.linePaint.setShader(null);
            mSurface.linePaint.setColor(0x40006ea7);
            mSurface.linePaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(blueJ * mWidth / 7, blueI * mHeight / 7, (blueJ + 1) * mWidth / 7, (blueI + 1) * mHeight / 7, mSurface
                    .linePaint);
            mSurface.linePaint.setStyle(Paint.Style.STROKE);
            mSurface.linePaint.setColor(0xffa0a0a0);
//            mSurface.linePaint.setShader(mSurface.mShader);
        }
        Calendar temp = Calendar.getInstance();
        temp.set(startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.MONTH), startCalendar.get(Calendar.DATE));
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Rect targetRect = new Rect((int) (j * mWidth / 7), (int) (i * mHeight / 7), (int) ((j + 1) * mWidth / 7), (int) ((i
                        + 1) * mHeight / 7));
                Paint.FontMetricsInt fontMetrics = mSurface.textPaint.getFontMetricsInt();
                int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
                if (i == 0)//固定写星期几
                    canvas.drawText(mSurface.weekDay[j], targetRect.centerX(), baseline, mSurface.weekDayPaint);
                else {
                    if (temp.get(Calendar.MONTH) == cCalendar.get(Calendar.MONTH)) {
//                        if (temp.get(Calendar.MONTH) == today.getMonth() && temp.get(Calendar.DATE) == today.getDate()) {//当前格子是今天
                        if (temp.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH) && temp.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)) {//当前格子是今天
                            mSurface.textPaint.setColor(Color.RED);
                            canvas.drawText(temp.get(Calendar.DATE) + "", targetRect.centerX(), baseline, mSurface
                                    .textPaint);
                            mSurface.textPaint.setColor(0xff404040);
                        } else if (j == 0 || j == 6) {//当前格子是周末
                            mSurface.textPaint.setColor(0XFFf28da8);
                            canvas.drawText(temp.get(Calendar.DATE) + "", targetRect.centerX(), baseline, mSurface
                                    .textPaint);
                            mSurface.textPaint.setColor(0xff404040);
                        } else//当前格子是工作日
                            canvas.drawText(temp.get(Calendar.DATE) + "", targetRect.centerX(), baseline, mSurface
                                    .textPaint);
                    } else {
                        canvas.drawText(temp.get(Calendar.DATE) + "", targetRect.centerX(), baseline, mSurface
                                .weekDayPaint);
                    }
                    temp.add(Calendar.DATE, 1);//标记给到下一天
                }
            }
        }
    }

    class Surface {
        Path box;
        Paint linePaint = new Paint();
        Paint weekDayPaint = new Paint();
        Paint textPaint = new Paint();
        Shader mShader = new LinearGradient(0, 0, 100, 100,
                new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW},
                null, Shader.TileMode.REPEAT);
        String[] weekDay = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        String[] monthDay = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

        private void init() {
//            linePaint.setShader(mShader);
            linePaint.setColor(0xffa0a0a0);
            linePaint.setAntiAlias(true);
            linePaint.setStrokeWidth(1);
            linePaint.setStyle(Paint.Style.STROKE);
            box = new Path();
            box.setFillType(Path.FillType.EVEN_ODD);
            for (int i = 0; i < 8; i++) {
                box.moveTo(0, i * mHeight / 7);
                box.rLineTo(mWidth, 0);
            }
            for (int i = 0; i < 8; i++) {
                box.moveTo(i * mWidth / 7, 0);
                box.rLineTo(0, mHeight);
            }
            weekDayPaint.setColor(0xff606060);
            textPaint.setColor(0xff404040);
            textPaint.setStrokeWidth(3);
            textPaint.setTextSize(mWidth / 8 / 2f);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setAntiAlias(true);
            weekDayPaint.setTextAlign(Paint.Align.CENTER);
            weekDayPaint.setAntiAlias(true);
            weekDayPaint.setTextSize(mWidth / 12 / 2f);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = measurePix(widthMeasureSpec);
        mHeight = measurePix(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private float downX, downY, upX, upY;
    private int blueI, blueJ;
    private boolean clicked = false;

    private void changeBlockColor(float x, float y) {
        blueI = (int) (y / (mHeight / 7));
        blueJ = (int) (x / (mWidth / 7));
        if (x > 0)
            clicked = true;
    }

    private OnItemClickListener mClickListener;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                changeBlockColor(downX, downY);
                break;
            case MotionEvent.ACTION_UP:
                clicked = false;
                upX = event.getX();
                upY = event.getY();
                if (Math.abs(upX - downX) > mWidth / 7 * 3 && Math.abs(upY - downY) < mHeight / 7 * 2) {
                    if (upX - downX > 0) {
                        lastMonth();
                    } else {
                        nextMonth();
                    }
                } else if (mClickListener != null) {
                    mClickListener.onItmeClick();
                }
                break;
        }
        invalidate();
        return true;
    }

    public void setCalendar(Calendar calendar) {
        cCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        startCalendar = Calendar.getInstance();
        Log.e("date", mSurface.monthDay[cCalendar.get(Calendar.MONTH)] + cCalendar.get(Calendar.DATE) + "日" + mSurface
                .weekDay[cCalendar.get(Calendar.DAY_OF_WEEK) - 1]);
        startCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int days = cCalendar.getActualMaximum(Calendar.DATE);
        int rows = 1;
        for (int i = 0; i < days; i++) {
            calendar.add(Calendar.DATE, 1);
            if (calendar.get(Calendar.DAY_OF_WEEK) == 0)
                rows++;
        }
        switch (rows) {
            case 4://一定是平二月并且1号是星期天
                startCalendar.add(Calendar.DAY_OF_MONTH, -7);
                break;
            default:
                Log.e("date", mSurface.monthDay[cCalendar.get(Calendar.MONTH)] + cCalendar.get(Calendar.DATE) + "日" + mSurface
                        .weekDay[cCalendar.get(Calendar.DAY_OF_WEEK) - 1]);
                startCalendar.add(Calendar.DAY_OF_MONTH, -(cCalendar.get(Calendar.DAY_OF_WEEK) - 1));
                break;
        }
        invalidate();
    }

    //根据xml的设定获取宽高
    private int measurePix(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //wrap_content
        if (specMode == MeasureSpec.AT_MOST) {
        }
        //fill_parent或者精确值
        else if (specMode == MeasureSpec.EXACTLY) {
        }
//        Log.i("这个控件的宽度----------", "specMode=" + specMode + " specSize=" + specSize);
        return specSize;
    }

    public void nextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(cCalendar.get(Calendar.YEAR), cCalendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, 1);
        setCalendar(calendar);
    }

    public void lastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(cCalendar.get(Calendar.YEAR), cCalendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        setCalendar(calendar);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public interface OnItemClickListener {
        public abstract Date onItmeClick();
    }
}
