package com.android.http;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by liushaobo on 2016/6/20.
 */

@SuppressWarnings("unused")
public abstract class HttpRequestImpl implements Http {

    private static final int REQ_TYPE_VOLLEY = 0x01;
    private static final int REQ_TYPE_THINK_ANDROID = 0x02;

    private static final int REQ_TYPE_DEFAULT = 0x01;

    /**
     * define custom http request instance.
     **/
    protected static HttpRequestImpl volleyInstance;
    protected static HttpRequestImpl thinkAndroidInstance;

    /**
     * define default http request instance.
     **/
    private static HttpRequestImpl defaultInstance;

    public static Http getDefaultInstance() {

        if (defaultInstance == null) {
            synchronized (HttpRequestImpl.class) {
                if (defaultInstance == null) {
                    switch (REQ_TYPE_DEFAULT) {
                        case REQ_TYPE_VOLLEY:
                            defaultInstance = new HttpRequestVolleyImpl();
                            break;
                        case REQ_TYPE_THINK_ANDROID:
                            defaultInstance = new HttpRequestThinkAndroidImpl();
                            break;
                    }
                }
            }
        }
        return defaultInstance;
    }

    public static Http getVolleyInstance() {

        if (volleyInstance == null) {
            synchronized (HttpRequestImpl.class) {
                if (volleyInstance == null) {
                    volleyInstance = new HttpRequestVolleyImpl();
                }
            }
        }
        return volleyInstance;
    }

    public static Http getThinkAndroidInstance() {

        if (thinkAndroidInstance == null) {
            synchronized (HttpRequestImpl.class) {
                if (thinkAndroidInstance == null) {
                    thinkAndroidInstance = new HttpRequestThinkAndroidImpl();
                }
            }
        }
        return thinkAndroidInstance;
    }


    @Override
    public void request(final Context context, final String url, final HashMap<String, String> params,
                        final onHttpListener listener) {
        request(context, Method.GET, url, params, listener);
    }

    @Override
    public void request(final Context context, final int method, final String url, final HashMap<String, String> params,
                        final onHttpListener listener) {
        switch (method) {
            case Method.GET:
            default:
                get(context, url, params, listener);
                break;
            case Method.POST:
                post(context, url, params, listener);
                break;
        }

    }


    abstract void get(Context context, String url, HashMap<String, String> params, onHttpListener listener);


    abstract void post(Context context, String url, HashMap<String, String> params, onHttpListener listener);
}
