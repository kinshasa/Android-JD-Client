package net.xicp.liushaobo.jd.ui.home;

import android.content.Context;

import com.android.http.Http;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.17.
 */

public interface HomeInteractor {

    void request(Context context, Http.onHttpListener listener);
}
