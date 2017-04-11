package net.xicp.liushaobo.jd;

import android.app.Application;

import net.xicp.liushaobo.jd.Logutils.L;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.11.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        L.v();
    }
}
