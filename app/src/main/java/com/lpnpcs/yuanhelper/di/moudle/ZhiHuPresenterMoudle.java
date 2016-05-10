package com.lpnpcs.yuanhelper.di.moudle;

import com.lpnpcs.yuanhelper.presenter.Contract.ZhiHuContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@Module
public class ZhiHuPresenterMoudle {
    private final ZhiHuContract.View mView;
    public ZhiHuPresenterMoudle(ZhiHuContract.View view){
        mView = view;
    }

    @Provides
    ZhiHuContract.View provideZhiHuContractView(){
        return mView;
    }


}
