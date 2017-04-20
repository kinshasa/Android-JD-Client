package net.xicp.liushaobo.jd.ui.home;

import net.xicp.liushaobo.jd.ui.home.model.FloorList;
import net.xicp.liushaobo.jd.ui.home.model.HomePage;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.17.
 */

public interface HomeView {

    void showLoading();

    void HideLoading();

    void onPullDownToRefresh();

    void onPullUpToRefresh();

    void getHomeData(HomePage homePage);

}
