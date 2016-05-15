package com.lpnpcs.yuanhelper.di.module;

import com.lpnpcs.yuanhelper.presenter.Contract.ZhiHuContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@Module
public class ZhiHuPresenterModule {
    private final ZhiHuContract.View mView;
    public ZhiHuPresenterModule(ZhiHuContract.View view){
        mView = view;
    }

    @Provides
    ZhiHuContract.View provideZhiHuContractView(){
        return mView;
    }


}
