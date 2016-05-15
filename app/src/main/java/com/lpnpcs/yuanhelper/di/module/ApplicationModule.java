package com.lpnpcs.yuanhelper.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lpnpcs
 * 全局moudle
 */
@Module
public class ApplicationModule {
    private final Context mContext;

    ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

}
