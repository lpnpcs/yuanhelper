package com.lpnpcs.yuanhelper.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lpnpcs.yuanhelper.base.YuanApplication;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：save SharedPreferences
 */
public class SettingUtil {
    private static Context context = YuanApplication.context;
    private static SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
    private static SharedPreferences.Editor editor = sp.edit();

    public static void save(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }
    public static void save(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static void save(String key, int value) {
        editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public static String get(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static String getString(String key) {
        return sp.getString(key, "");
    }
    public static int getInt(String key) {
        return sp.getInt(key, 0);
    }
    public static boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }
}
