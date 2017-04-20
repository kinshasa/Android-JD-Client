package net.xicp.liushaobo.jd.ui.home;

import android.content.Context;

import com.android.log.L;

import net.xicp.liushaobo.jd.ui.home.model.HomePage;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeInteractor interactor;
    private HomeView homeView;


    HomePresenterImpl(HomeView view) {
        homeView = view;
        interactor = new HomeInteractorImpl();
    }

    @Override
    public void fetch(Context context) {
        L.v();
        interactor.request(context, new HomeInteractor.onFetchListener() {
            @Override
            public void onSuccess(HomePage homePage) {
                homeView.getHomeData(homePage);
            }

            @Override
            public void onFail(Object exceptionInfo) {
                L.v(exceptionInfo);

            }
        });
    }
}
