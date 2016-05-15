package com.lpnpcs.yuanhelper.di.module;

import com.lpnpcs.yuanhelper.presenter.Contract.ZhiHuDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@Module
public class ZhiHuDetailPresenterModule {
    private final ZhiHuDetailContract.View mView;
    public ZhiHuDetailPresenterModule(ZhiHuDetailContract.View view){
        mView = view;
    }
    @Provides
    public ZhiHuDetailContract.View provideZhiHuDetailContractView(){
        return mView;
    }
}
