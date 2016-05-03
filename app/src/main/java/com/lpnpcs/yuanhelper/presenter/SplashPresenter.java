package com.lpnpcs.yuanhelper.presenter;

import com.lpnpcs.yuanhelper.data.net.HttpMethods;
import com.lpnpcs.yuanhelper.presenter.Contract.SplashContract;
import com.lpnpcs.yuanhelper.util.LogUtil;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public class SplashPresenter implements SplashContract.Presenter {
        private final SplashContract.View mSplashView;
        private Subscriber subscriber;

    @Inject
    SplashPresenter(SplashContract.View view){
                mSplashView = view;
    }

    @Override

    public void getSplash() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("lp",e.getMessage());
                mSplashView.setSplash(null);
            }

            @Override
            public void onNext(String s) {
               mSplashView.setSplash(s);
            }

        };
        HttpMethods.getInstance().getSplash(subscriber);
    }

    @Override
    public void start() {

    }
}
