package com.zhou.myweather.sdk.core;

import android.content.Context;
import android.os.Handler;
import android.os.Message;


//import com.android.volley.Request;
import com.zhou.myweather.sdk.defines.protocol.IServiceRequest;
import com.zhou.myweather.sdk.defines.protocol.IServiceResponse;
import com.zhou.myweather.util.LogUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Created by zhou on 2015-09-10.
 */
public class HttpEngine extends BaseEngine {
    private final String TAG = HttpEngine.class.getName();
    private static HttpEngine httpEngine = null;
//    private AsyncHttpClient httpClient = new AsyncHttpClient();
    private List<Handler> handlers;

    private HttpEngine() {
        handlers = new ArrayList<Handler>();
    }

    private static synchronized void syncInit() {
        if (httpEngine == null) {
            httpEngine = new HttpEngine();
        }
    }

    public static HttpEngine getEngine() {
        if (httpEngine == null) {
            syncInit();
        }
        return httpEngine;
    }

    public void addListenerHandler(Handler handler) {
        if (handler != null && !handlers.contains(handler))
            handlers.add(handler);
    }

    public void removeListenerHandler(Handler handler) {
        if (handler != null && handlers.contains(handler))
            handlers.remove(handler);
    }

//    public RequestHandle executeHttp(Context context, IServiceRequest request, final Class<? extends IServiceResponse> responseClass) {
//        return executeHttp(context, request, responseClass, null);
//    }

    //    public RequestHandle executeHttp(Context context,IServiceRequest request, final Class<? extends IServiceResponse> responseClass) {
//        return executeHttp(request, responseClass, null);
//    }
//    public RequestHandle senRequest(IServiceRequest request, final Class<? extends IServiceResponse> responseClass, final HttpRequestListener listener) {
//        return executeHttp(null, request, responseClass, listener);
//    }

//    public RequestHandle executeHttp(final Context context, IServiceRequest request, final Class<? extends IServiceResponse> responseClass, final HttpRequestListener listener) {
//        if (!request.validateParams()) {
//            LogUtil.w(TAG, "请求参数无效");
//            return null;
//        } else if (responseClass == null) {
//            LogUtil.w(TAG, "应答类为空");
//            return null;
//        }

//        if()

//        AsyncHttpResponseHandler asyncHttpResponseHandler = new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                if (bytes.length == 0) {
//                    LogUtil.w(TAG, "接收为空！");
//                    return;
//                }
//                try {
//                    IServiceResponse response = responseClass.newInstance();
//                    LogUtil.sysout(TAG, "http ret:" + new String(bytes));
//                    response.initData(bytes);
//                    for (Handler handler : handlers) {
//                        handler.sendMessage(Message.obtain(null, NET_RECIVED_SUCCESS, response));
//                    }
//
//                    if (listener != null) {
//                        listener.responseResult(response);
//                    }
//
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                String failMsg = bytes == null ? "" : new String(bytes);
//                if (bytes != null) {
//                    LogUtil.sysout(TAG, "http ret:" + new String(bytes));
//                } else {
//                    LogUtil.w(TAG, "请求失败，返回为空");
//                }
//                for (Handler handler : handlers) {
//                    handler.sendMessage(Message.obtain(null, NET_RECIVED_FAILURE, new String(failMsg)));
//                }
//            }
//        };
//        Map<String, String> header = request.getHeader();
//        if (header != null && header.size() > 0) {
//            LogUtil.sysout(TAG, "Header: " + header);
//            for (Entry<String, String> entry : header.entrySet()) {
//                httpClient.addHeader(entry.getKey(), entry.getValue());
//            }
//        }
//        if (request.getMethod() == Request.Method.GET) {
//            String url = request.getURL();
//            url = url.replace(" ", "20%");
//            if (request.getEntity() != null)
//                url += (String) request.getEntity();
//            LogUtil.sysout(TAG, "url: " + url);
//            return httpClient.get(url, asyncHttpResponseHandler);
//        }
//        return null;
//    }

    public static interface HttpRequestListener {
        public void responseResult(IServiceResponse response);
    }

}