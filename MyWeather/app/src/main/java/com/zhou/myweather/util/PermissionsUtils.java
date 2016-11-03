package com.zhou.myweather.util;

import android.Manifest;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;

import com.zhou.sdk.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Powerbee on 2016/5/27.
 */
public class PermissionsUtils {
    private static List<String> permissionsList = new ArrayList<>();
    private static List<String> unPermission = new ArrayList<>();

    static{
        permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);//存储权限
        permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION);//精确位置权限
        permissionsList.add(Manifest.permission.READ_CALENDAR);//日期
    }

    public static String[] getpermissionLists() {
        if (permissionsList.size() == 0) {
            LogUtil.e("PermissionsUtils", "");
            return null;
        }
        return (String[]) unPermission.toArray(new String[unPermission.size()]);
    }

    public static boolean isNeedPermission(Activity activity) {
        for (String permission : permissionsList)
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                unPermission.add(permission);
            }
        return unPermission.size() != 0;
    }

}
