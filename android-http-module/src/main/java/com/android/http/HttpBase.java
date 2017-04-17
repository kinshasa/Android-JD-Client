package com.android.http;

/**
 * Created by liushaobo.xicp.net on 2016/6/13.
 *
 */
@SuppressWarnings("unused")
public class HttpBase {



    public static Http getDefaultInstance(){

        return HttpRequestImpl.getDefaultInstance();
    }

    public static Http getVolleyInstance(){

        return HttpRequestImpl.getVolleyInstance();
    }

    public static Http getThinkAndroidInstance(){

        return HttpRequestImpl.getThinkAndroidInstance();
    }

}
