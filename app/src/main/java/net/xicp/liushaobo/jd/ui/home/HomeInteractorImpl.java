package net.xicp.liushaobo.jd.ui.home;

import android.content.Context;

import com.android.http.Http;
import com.android.http.HttpBase;

import net.xicp.liushaobo.jd.ui.home.model.HomePage;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.17.
 */

public class HomeInteractorImpl implements HomeInteractor{

    String url = "http://api.m.jd.com/client.action?functionId=welcomeHome&clientVersion=6.0.0&build=45473&client=android&d_brand=Xiaomi&d_model=Redmi3S&osVersion=6.0.1&screen=1280*720&partner=xiaomi001&uuid=862212031900200-742344d8eba4&area=19_1601_3633_0&networkType=wifi&st=1492417616055&sign=b295ee284c614e85ae7e62ec22116b34&sv=121&body=%7B%22geo%22%3A%7B%22lng%22%3A%22113.241462%22%2C%22lat%22%3A%2223.100102%22%7D%2C%22poz%22%3A%7B%22time%22%3A1492417605693%2C%22city%22%3A%22%E5%B9%BF%E4%B8%9C%3A%E5%B9%BF%E5%B7%9E%22%7D%2C%22identity%22%3A%22862212031900200-742344d8eba4%22%2C%22allLastTime%22%3A%220%22%2C%22cycFirstTimeStamp%22%3A%221492417605689%22%2C%22cycNum%22%3A1%7D&";
    private HomePage homePage;

    HomeInteractorImpl(){
    }

    @Override
    public void request(Context context, final onFetchListener listener) {
        HttpBase.getDefaultInstance().request(context, url, null, new Http.onHttpListener() {
            @Override
            public void onComplete(String values) {
                homePage = new HomePage(values);
                listener.onSuccess(homePage);
            }

            @Override
            public void onException(Object exceptionInfo) {
                listener.onFail(exceptionInfo);
            }
        });
    }

}
