package com.android.http;

import android.content.Context;

import com.android.http.util.StrUtil;
import com.android.log.L;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liushaobo.xicp.net on 2016/6/12.
 */
public class YHttpRequest {


    private static final int REQ_TYPE_DEFAULT = 0x01;
    private static final int REQ_TYPE_THINK_ANDROID = 0x02;
    private static final int REQ_TYPE_VOLLEY = 0x04;

    public static YHttpRequest defaultInstance;

    /** Convenience singleton for apps using a process-wide YHttpRequest defaultInstance. */
    public static YHttpRequest getInstance() {

        if (defaultInstance == null) {
            synchronized (YHttpRequest.class) {
                if (defaultInstance == null) {
                    defaultInstance = new YHttpRequest();
                }
            }
        }
        return defaultInstance;
    }



    public void request(final Context context,final String url, final HashMap<String, String> params,
                        final YAsyncListener asyncListener) {
        request("GET",context,url, params, asyncListener, REQ_TYPE_DEFAULT);
    }

    public void request(final Context context, final String url, final HashMap<String, String> params,
                        final YAsyncListener asyncListener, final int type) {
        request("GET",context,url, params, asyncListener, type);
    }

    public void request(final String method,final Context context,final String url, final HashMap<String, String> params,
                        final YAsyncListener asyncListener) {
        request(method,context,url, params, asyncListener, REQ_TYPE_DEFAULT);
    }

    public void request(final String method, final Context context, final String url, final HashMap<String, String> params,
                        final YAsyncListener asyncListener, final int type) {

        switch (method) {
            case "GET":
            default:
                get(url, params, context, asyncListener, type);
                break;
            case "POST":
                post(url, params, context, asyncListener, type);
                break;
        }

    }

    private void get(final String url, final HashMap<String, String> params,
                     final Context context, final YAsyncListener asyncListener, final int type) {

        switch (type) {
            case REQ_TYPE_DEFAULT:
            case REQ_TYPE_VOLLEY:
                getByVolley(url, params, context, asyncListener);
                break;
            case REQ_TYPE_THINK_ANDROID:
                getByThinkAndroid();
                break;

        }

    }

    private void post(final String url, final HashMap<String, String> params,
                      final Context context, final YAsyncListener asyncListener, final int type) {

        switch (type) {
            case REQ_TYPE_DEFAULT:
            case REQ_TYPE_VOLLEY:
                postByVolley(url, params, context, asyncListener);
                break;
            case REQ_TYPE_THINK_ANDROID:
                getByThinkAndroid();
                break;

        }

    }

    private void getByVolley(final String str, final HashMap<String, String> params,
                             final Context context, final YAsyncListener asyncListener) {

        String url = new StrUtil().encodeUrl(str,params).toString();

        StringRequest stringRequest = new StringRequest(url,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        L.v(response);

                        try {
                            asyncListener.onComplete(response);
                        }catch (Exception e){
                            e.printStackTrace();
                            asyncListener.onException(e);
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        L.v(error);
                        asyncListener.onException(error.getMessage());
                    }
                });

        L.v(stringRequest.getUrl());
        VolleySingleton.startVolley(context, stringRequest);

    }

    private void postByVolley(final String str, final HashMap<String, String> params,
                              final Context context, final YAsyncListener asyncListener) {

        String temp = new StrUtil().encodeUrl(str,params).toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, str,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        L.v(response);
                        try {
                            asyncListener.onComplete(response);
                        }catch (Exception e){
                            e.printStackTrace();
                            asyncListener.onException(response);
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        L.v(error.getMessage());
                        asyncListener.onException(error.getMessage());
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return params;
                    }
                };

        L.v(temp);
        VolleySingleton.startVolley(context, stringRequest);

    }

    private void getByThinkAndroid() {

    }
}
