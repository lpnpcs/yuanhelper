package com.lpnpcs.yuanhelper.di.component;

import com.lpnpcs.yuanhelper.di.module.SplashPresenterModule;
import com.lpnpcs.yuanhelper.presenter.SplashPresenter;

import dagger.Component;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@Component(modules = SplashPresenterModule.class)
public interface SplashComponent {
      SplashPresenter getSplashPresenter ();
}
