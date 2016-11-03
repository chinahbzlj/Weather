package com.zhou.sdk.core;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zhou.sdk.defines.protocol.IServiceRequest;
import com.zhou.sdk.defines.protocol.IServiceResponse;
import com.zhou.sdk.util.LogUtil;

import java.util.HashMap;
import java.util.Map;

public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mContext;

    public VolleySingleton(Context context) {
        this.mContext = context;
        mRequestQueue = getRequestQueue();
//        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
//            private final LruCache cache = new LruCache<String, Bitmap>(20)
//
//            @Override
//            public Bitmap getBitmap(String url) {
//                return cache.get(url);
//            }
//
//            @Override
//            public void putBitmap(String url, Bitmap bitmap) {
//                cache.put(url, bitmap);
//            }
//        });
    }

    public static synchronized VolleySingleton getVolleySingleton(Context context) {
        if (volleySingleton == null) {
            volleySingleton = new VolleySingleton(context);
        }
        return volleySingleton;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void senRequest(final IServiceRequest iServiceRequest, final Class<? extends IServiceResponse> responseClass, final HttpRequestListener httpRequestListener) {
        if (!iServiceRequest.validateParams()) {
            LogUtil.w("VolleySingleton", "请求参数错误");
            return;
        }
        if (httpRequestListener == null) {
            return;
        }
        String url = null;
        if(iServiceRequest.getMethod() == Request.Method.GET){
            url = iServiceRequest.getURL()+iServiceRequest.getEntity();
        }
        Log.e("volley", url);
        StringRequest stringRequest = new StringRequest(iServiceRequest.getMethod(), url,
                /**
                 * 请求成功
                 */
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //打印请求返回结果
                        Log.e("volley", s);
                        try {
                            IServiceResponse response = responseClass.newInstance();
                            response.initData(s);
                            if (httpRequestListener != null)
                                httpRequestListener.responseResult(response);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                },
                /**
                 * 请求失败
                 */
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("volleyerror", "erro2");
//            listener.responseResult();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
//                if(iServiceRequest.getEntity())
                Map<String, String> map = new HashMap<String, String>();
                map.put("params1", "value1");
                map.put("params2", "value2");
                return map;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                if (iServiceRequest.getHeader() != null)
//                    return iServiceRequest.getHeader();
//                return super.getHeaders();
//            }
        };
        getRequestQueue().add(stringRequest);
    }


    public interface HttpRequestListener {
        void responseResult(IServiceResponse response);
    }
}