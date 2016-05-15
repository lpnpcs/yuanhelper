package com.lpnpcs.yuanhelper.di.component;

import com.lpnpcs.yuanhelper.di.module.ZhiHuPresenterModule;
import com.lpnpcs.yuanhelper.presenter.ZhiHuPresenter;
import com.lpnpcs.yuanhelper.util.FragmentScoped;

import dagger.Component;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@FragmentScoped
@Component(modules  = ZhiHuPresenterModule.class)
public interface ZhiHuFragmetComponent {
    ZhiHuPresenter getZhiHuPresenter();
}
