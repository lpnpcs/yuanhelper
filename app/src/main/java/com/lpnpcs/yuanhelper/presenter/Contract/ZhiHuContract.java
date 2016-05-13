package com.lpnpcs.yuanhelper.presenter.Contract;

import com.lpnpcs.yuanhelper.base.BasePresenter;
import com.lpnpcs.yuanhelper.base.BaseView;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuEntity;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：This specifies the contract between the view and the presenter.
 */
public interface ZhiHuContract {
    interface View extends BaseView<Presenter> {
        void  addNews(ZhiHuEntity zhiHuEntity);
        void addBeforeNews(ZhiHuEntity zhiHuEntity);
        void showProgress();
        void hideProgress();
        void loadFailed(String msg);

    }

    interface Presenter extends BasePresenter {
        void  loadNews();
        void loadBefore();
    }

}
