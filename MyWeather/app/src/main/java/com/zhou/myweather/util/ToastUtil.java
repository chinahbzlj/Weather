package com.zhou.myweather.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by zhoulijie on 2017/4/10.
 */

public class ToastUtil extends Handler {
    private static Context mContext;
    private static ToastUtil util = null;


    private ToastUtil() {
    }

    private static synchronized void syncInit(Context context) {
        if (util == null) {
            util = new ToastUtil();
            mContext = context;
        }
    }

    public static ToastUtil getInstance() {
        if (util == null) {
            syncInit(mContext);
        }
        return util;

    }

    public void setApplicatonContext(Context context) {
        mContext = context;
    }

    public void toastShowS(final Context context, final String str) {
        if (context == null && TextUtils.isEmpty(str)) return;
        this.post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(str)) {
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj = str;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    public void toastShowS(final String str) {
        if (mContext == null && TextUtils.isEmpty(str)) return;
        this.post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(str)) {
                    Message msg = new Message();
                    msg.what = 2;
                    msg.obj = str;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    public void toastShowL(final String str) {
        if (mContext == null && TextUtils.isEmpty(str)) return;
        this.post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(str)) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = str;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    public void toastShowS(Context context, final int resId) {
        if (context == null && resId == 0) return;
        toastShowS(context, context.getString(resId));
    }

    public void toastShowL(final Context context, final String str) {
        if (context == null && TextUtils.isEmpty(str)) return;
        this.post(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(str)) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = str;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    public void toastShowL(final Context context, final int resId) {
        if (context == null && resId == 0) return;
        toastShowL(context, context.getString(resId));
    }


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(mContext, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(mContext, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }

        }
    };

//    public void postDelayed(Runnable runnable, long delayMillis) {
//        handler.postDelayed(runnable, delayMillis);
//    }
}
