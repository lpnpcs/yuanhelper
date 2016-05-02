package com.lpnpcs.yuanhelper.base;

import android.app.Application;

import com.lpnpcs.yuanhelper.util.CrashHandler;

/**
 * Created by lpnpcs
 *
 */
public class YuanApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
