package com.lpnpcs.yuanhelper.di.module;

import com.lpnpcs.yuanhelper.presenter.Contract.JokeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@Module
public class JokePresenterModule {
        private final JokeContract.View mView;
    public JokePresenterModule(JokeContract.View view){
        mView = view;
    }
    @Provides
    JokeContract.View provideJokeContractView(){
        return mView;
    }

}
