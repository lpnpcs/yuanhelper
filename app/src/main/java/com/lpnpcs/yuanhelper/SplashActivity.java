package com.lpnpcs.yuanhelper;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.data.net.HttpMethods;
import com.lpnpcs.yuanhelper.data.net.SplashService;
import com.lpnpcs.yuanhelper.util.API;
import com.lpnpcs.yuanhelper.util.LogUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;

public class SplashActivity extends AppCompatActivity {

    ImageView splash;
    private Subscriber subscriber;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        splash = (ImageView) findViewById(R.id.splash);
        initSplash();
    }

    private void initSplash() {

        getSplash();
    }

    private void getSplash() {
        LogUtil.d("lp"," sss");

        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("lp",e.getMessage());
            }

            @Override
            public void onNext(String s) {
                LogUtil.d("lp"," sss"+s);
                Glide.with(getApplicationContext())
                        .load(s)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .crossFade()
                        .into(splash);
            }

        };
        HttpMethods.getInstance().getSplash(subscriber);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
