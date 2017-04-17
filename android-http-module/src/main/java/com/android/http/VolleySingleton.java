package com.android.http;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by liushaobo.xicp.net on 2016/6/12.
 */
public class VolleySingleton {

    private static VolleySingleton volleySingleton;
    private RequestQueue mRequestQueue;

    private VolleySingleton(Context context) {
        mRequestQueue =  Volley.newRequestQueue(context);
    }

    private static synchronized VolleySingleton getVolleySingleton(Context context) {
        if (volleySingleton == null) {
            volleySingleton = new VolleySingleton(context);
        }
        return volleySingleton;
    }


    public static void startVolley(Context context, StringRequest stringRequest) {
        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 0, 1.0f));
        getVolleySingleton(context).mRequestQueue.add(stringRequest);

    }

}
