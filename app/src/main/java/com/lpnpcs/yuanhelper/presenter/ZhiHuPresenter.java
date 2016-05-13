package com.lpnpcs.yuanhelper.presenter;

import com.lpnpcs.yuanhelper.data.entity.ZhiHuEntity;
import com.lpnpcs.yuanhelper.data.net.HttpMethods;
import com.lpnpcs.yuanhelper.presenter.Contract.ZhiHuContract;
import com.lpnpcs.yuanhelper.util.Constants;
import com.lpnpcs.yuanhelper.util.DateUtil;
import com.lpnpcs.yuanhelper.util.SettingUtil;

import java.util.Date;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public class ZhiHuPresenter implements ZhiHuContract.Presenter {
    private Subscriber<ZhiHuEntity> subscriber;
    private final ZhiHuContract.View mZhiHuView;
    private String date;
    @Inject
    ZhiHuPresenter(ZhiHuContract.View view) {
        mZhiHuView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadNews() {
        mZhiHuView.showProgress();
        subscriber = new Subscriber<ZhiHuEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mZhiHuView.hideProgress();
                mZhiHuView.loadFailed(e.toString());
            }

            @Override
            public void onNext(ZhiHuEntity zhiHuEntity) {
               // LogUtil.e("lp",zhiHuEntity.toString());
                date = zhiHuEntity.getDate();
                SettingUtil.save(Constants.DATE, date);
                mZhiHuView.addNews(zhiHuEntity);
                mZhiHuView.hideProgress();
            }
        };
        HttpMethods.getInstance().getZhiHuLatest(subscriber);
    }

    @Override
    public void loadBefore() {
        mZhiHuView.showProgress();
        subscriber = new Subscriber<ZhiHuEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mZhiHuView.hideProgress();
                mZhiHuView.loadFailed(e.toString());
            }

            @Override
            public void onNext(ZhiHuEntity zhiHuEntity) {
               // LogUtil.e("lp",zhiHuEntity.toString());
                date = zhiHuEntity.getDate();
                SettingUtil.save(Constants.DATE, date);
                mZhiHuView.addBeforeNews(zhiHuEntity);
                mZhiHuView.hideProgress();
            }
        };
        date = SettingUtil.get(Constants.DATE, DateUtil.parseStandardDate(new Date()));
        HttpMethods.getInstance().getZhiHuBefore(subscriber,date);
    }
}
