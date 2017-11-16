package com.zhou.myweather.util;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 周利杰 on 2017/7/18.
 */

public class ActivityUtils {
    private static ActivityUtils activityUtils;
    private Stack<Activity> activities = new Stack<>();

    private ActivityUtils() {
    }

    private static synchronized void syncInit() {
        if (activityUtils == null)
            activityUtils = new ActivityUtils();
    }

    public static ActivityUtils getActivityUtils() {
        if (activityUtils == null)
            synchronized (ActivityUtils.class) {
                if (activityUtils == null)
                    activityUtils = new ActivityUtils();
            }
        return activityUtils;
    }

    public void pushActivity(Activity activity) {
        activities.push(activity);
    }

    public void exit() {
        while (!activities.empty()) {
            activities.pop().finish();
        }
    }

    public void popActivity(Activity activity) {
        if (!activities.isEmpty() && activities.peek() == activity) {
            activities.pop();
        }
    }
}
