package com.lpnpcs.yuanhelper.presenter.Contract;

import com.lpnpcs.yuanhelper.base.BasePresenter;
import com.lpnpcs.yuanhelper.base.BaseView;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：This specifies the contract between the view and the presenter.
 */
public interface ZhiHuContract {
    interface View extends BaseView<Presenter> {
            void  addNews();

    }

    interface Presenter extends BasePresenter {
        void  loadNews();
    }

}
