package com.lpnpcs.yuanhelper.di.moudle;

import com.lpnpcs.yuanhelper.presenter.Contract.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */

@Module
public class SplashPresenterMoudle {
    private final SplashContract.View mView;

    public SplashPresenterMoudle(SplashContract.View view) {
        mView = view;
    }
    @Provides
    SplashContract.View provideSplashContractView(){
        return  mView;
    }

}
