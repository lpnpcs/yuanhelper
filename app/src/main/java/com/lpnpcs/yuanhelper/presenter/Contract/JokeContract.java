package com.lpnpcs.yuanhelper.presenter.Contract;

import com.lpnpcs.yuanhelper.base.BasePresenter;
import com.lpnpcs.yuanhelper.base.BaseView;
import com.lpnpcs.yuanhelper.data.entity.ImageEntity;
import com.lpnpcs.yuanhelper.data.entity.JokeEntity;

import java.util.ArrayList;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public interface JokeContract {
    interface View extends BaseView<Presenter> {
        void  addJoke(ArrayList<JokeEntity> data);
        void  addImage(ArrayList<ImageEntity> data);
        void showProgress();
        void hideProgress();
        void loadFailed(String msg);

    }

    interface Presenter extends BasePresenter {
        void getJoke(int type);
    }
}
