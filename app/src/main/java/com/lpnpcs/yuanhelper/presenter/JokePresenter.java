package com.lpnpcs.yuanhelper.presenter;

import com.lpnpcs.yuanhelper.data.entity.ImageEntity;
import com.lpnpcs.yuanhelper.data.entity.JokeEntity;
import com.lpnpcs.yuanhelper.data.net.HttpMethods;
import com.lpnpcs.yuanhelper.presenter.Contract.JokeContract;
import com.lpnpcs.yuanhelper.ui.fragment.JokeFragment;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public class JokePresenter implements JokeContract.Presenter {
    private final JokeContract.View mView;
    private Subscriber<JokeEntity> subscriber;
    private Subscriber<ImageEntity> subscriber_im;

    @Inject
    public JokePresenter(JokeContract.View view) {
        mView = view;
    }


    @Override
    public void getJoke(int type) {
        mView.showProgress();
        if (type == JokeFragment.TYPE_JOKE) {
            subscriber = new Subscriber<JokeEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mView.loadFailed(e.getMessage());
                    mView.hideProgress();
                }

                @Override
                public void onNext(JokeEntity jokeEntity) {
                    mView.addJoke(jokeEntity);
                    mView.hideProgress();
                }
            };
            HttpMethods.getInstance().getJoke(subscriber);
        } else if (type == JokeFragment.TYPE_JOKE_IMAGE) {
            subscriber_im = new Subscriber<ImageEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mView.loadFailed(e.getMessage());
                    mView.hideProgress();
                }

                @Override
                public void onNext(ImageEntity imageEntity) {
                    mView.addJoke(imageEntity);
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
