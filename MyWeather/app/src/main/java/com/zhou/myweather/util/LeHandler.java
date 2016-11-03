/**
 * 
 */
package com.zhou.myweather.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author Guo Bo
 * 
 */
public class LeHandler extends Handler {

	private static LeHandler mInstance;
	private static Context mContext;

	public static void init(Context context) {
		if (mInstance == null) {
			mInstance = new LeHandler();
			mContext = context;
		}
	}

	public static LeHandler getInstance() {
		if (mInstance == null) {
			init(mContext);
		}
		return mInstance;
	}

	public static void setActivityContext(Context context) {
		mContext = context;
	}

	public static Context getActivityContext() {
		return mContext;
	}

	public void toastShow(final Context context, final int resId) {
		if (context == null || 0 == resId) return;
		toastShow(context, context.getString(resId));
	}

	public void toastShow(final Context context, final String str) {
		if(context == null || TextUtils.isEmpty(str)) return;
		this.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				if (!TextUtils.isEmpty(str)) {
					Message msg = new Message();
					msg.what = 2;
					msg.obj = str;
					handler.sendMessage(msg );
				}
				
			}
		});
	}

	public void onDestroy() {
		mInstance = null;
		mContext = null;
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 2:
				Toast.makeText(mContext, msg.obj.toString(), Toast.LENGTH_LONG).show();
				break;

			default:
				break;
			}
			
		}
	};
}
