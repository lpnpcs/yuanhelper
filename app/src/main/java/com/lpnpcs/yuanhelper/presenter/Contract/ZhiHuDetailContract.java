package com.lpnpcs.yuanhelper.presenter.Contract;

import com.lpnpcs.yuanhelper.base.BasePresenter;
import com.lpnpcs.yuanhelper.base.BaseView;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuDetailEntity;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public interface ZhiHuDetailContract {
    interface View extends BaseView<Presenter>{
        void showProgress();
        void showDetail(ZhiHuDetailEntity zhiHuDetailEntity);
        void hideProgress();
        void showLoadFailed(String msg);
    }
    interface  Presenter extends BasePresenter{
        void loadNewsDetail(String id);
    }
}
