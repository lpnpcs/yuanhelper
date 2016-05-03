package com.lpnpcs.yuanhelper.base;

import android.app.Application;
import android.content.Context;

import com.lpnpcs.yuanhelper.util.CrashHandler;

/**
 * Created by lpnpcs
 */
public class YuanApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        CrashHandler.getInstance().init(this);
    }
}
