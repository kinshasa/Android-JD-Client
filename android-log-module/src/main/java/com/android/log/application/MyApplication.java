package com.android.log.application;

import android.app.Application;


/**
 * Created by liushaobo.xicp.net on 2016/6/14.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance=this;
    }


    public  static MyApplication getInstance(){
        return instance;
    }
}
