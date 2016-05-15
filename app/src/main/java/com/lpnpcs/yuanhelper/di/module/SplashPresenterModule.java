package com.lpnpcs.yuanhelper.di.module;

import com.lpnpcs.yuanhelper.presenter.Contract.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */

@Module
public class SplashPresenterModule {
    private final SplashContract.View mView;

    public SplashPresenterModule(SplashContract.View view) {
        mView = view;
    }
    @Provides
    SplashContract.View provideSplashContractView(){
        return  mView;
    }

}
