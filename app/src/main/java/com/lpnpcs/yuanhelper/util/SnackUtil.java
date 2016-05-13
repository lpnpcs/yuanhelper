package com.lpnpcs.yuanhelper.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.lpnpcs.yuanhelper.base.YuanApplication;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：Util to show hint such as snackBar or dialog.
 */
public class SnackUtil {
    private static Context context = YuanApplication.context;

    public static void showSnack(View rootView, int textId) {
        if (null != rootView) {
            Snackbar.make(rootView, textId, Snackbar.LENGTH_SHORT).show();
        }
    }

    public static void showSnackLong(View rootView, int textId) {
        if (null != rootView) {
            Snackbar.make(rootView, textId, Snackbar.LENGTH_LONG).show();
        }
    }
}
