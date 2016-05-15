package com.lpnpcs.yuanhelper.presenter;

import com.lpnpcs.yuanhelper.data.entity.ZhiHuDetailEntity;
import com.lpnpcs.yuanhelper.data.net.HttpMethods;
import com.lpnpcs.yuanhelper.presenter.Contract.ZhiHuDetailContract;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public class ZhiHuDetailPresenter implements ZhiHuDetailContract.Presenter {
    private final ZhiHuDetailContract.View mView;
    private Subscriber<ZhiHuDetailEntity> subscriber;

    @Inject
    public ZhiHuDetailPresenter(ZhiHuDetailContract.View view) {
        mView = view;
    }

    @Override
    public void loadNewsDetail(String id) {
        mView.showProgress();
        subscriber = new Subscriber<ZhiHuDetailEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showLoadFailed(e.getMessage());
                mView.hideProgress();
            }

            @Override
            public void onNext(ZhiHuDetailEntity zhiHuDetailEntity) {
                mView.showDetail(zhiHuDetailEntity);
                mView.hideProgress();
            }
        };
        HttpMethods.getInstance().getZhiHuDetail(subscriber, id);
    }

    @Override
    public void start() {

    }
}
