package com.lpnpcs.yuanhelper.di.moudle;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lpnpcs
 * 全局moudle
 */
@Module
public class ApplicationMoudle {
    private final Context mContext;

    ApplicationMoudle(Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

}
