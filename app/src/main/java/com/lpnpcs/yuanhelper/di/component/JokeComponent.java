package com.lpnpcs.yuanhelper.di.component;

import com.lpnpcs.yuanhelper.di.module.JokePresenterModule;
import com.lpnpcs.yuanhelper.presenter.JokePresenter;
import com.lpnpcs.yuanhelper.util.FragmentScoped;

import dagger.Component;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@FragmentScoped
@Component(modules  = JokePresenterModule.class)
public interface JokeComponent {
    JokePresenter getJokePresenter();
}
