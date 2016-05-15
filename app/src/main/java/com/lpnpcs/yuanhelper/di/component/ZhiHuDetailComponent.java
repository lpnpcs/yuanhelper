package com.lpnpcs.yuanhelper.di.component;

import com.lpnpcs.yuanhelper.di.module.ZhiHuDetailPresenterModule;
import com.lpnpcs.yuanhelper.presenter.ZhiHuDetailPresenter;

import dagger.Component;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@Component(modules = ZhiHuDetailPresenterModule.class)
public interface ZhiHuDetailComponent {
        ZhiHuDetailPresenter getZhiHuDetailPresenter();
}
