package net.xicp.liushaobo.jd.ui.home;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.17.
 */

public interface HomeView {

    void showLoading();
    void HideLoading();
    void onPullDownToRefresh();
    void onPullUpToRefresh();
}
