package com.lpnpcs.yuanhelper.presenter;

import com.lpnpcs.yuanhelper.data.entity.ImageEntity;
import com.lpnpcs.yuanhelper.data.entity.JokeEntity;
import com.lpnpcs.yuanhelper.data.net.HttpMethods;
import com.lpnpcs.yuanhelper.presenter.Contract.JokeContract;
import com.lpnpcs.yuanhelper.ui.fragment.JokeFragment;
import com.lpnpcs.yuanhelper.util.LogUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public class JokePresenter implements JokeContract.Presenter {
    private final JokeContract.View mView;
    private Subscriber<ArrayList<JokeEntity>> subscriber;
    private Subscriber<ArrayList<ImageEntity>> subscriber_im;

    @Inject
    public JokePresenter(JokeContract.View view) {
        mView = view;
    }


    @Override
    public void getJoke(int type) {
        mView.showProgress();
        if (type == JokeFragment.TYPE_JOKE) {
            subscriber = new Subscriber<ArrayList<JokeEntity>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    LogUtil.e("lp",e.toString());
                    mView.loadFailed(e.getMessage());
                    mView.hideProgress();
                }

                @Override
                public void onNext(ArrayList<JokeEntity> jokeEntities) {
                    mView.addJoke(jokeEntities);
                    mView.hideProgress();
                }
            };
            HttpMethods.getInstance().getJoke(subscriber);
        } else if (type == JokeFragment.TYPE_JOKE_IMAGE) {
            subscriber_im = new Subscriber<ArrayList<ImageEntity>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mView.loadFailed(e.getMessage());
                    mView.hideProgress();
                }

                @Override
                public void onNext(ArrayList<ImageEntity> imageEntities) {
                    mView.addImage(imageEntities);
                    mView.hideProgress();
                }
            };
            HttpMethods.getInstance().getJokeImage(subscriber_im);
        }
    }

    @Override
    public void start() {

    }
}
